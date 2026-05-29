CREATE OR REPLACE FUNCTION fnc_sentiment(p_text IN CLOB) RETURN VARCHAR2 IS
    v_text VARCHAR2(4000);
    v_pos NUMBER := 0;
    v_neg NUMBER := 0;
    TYPE t_word_list IS TABLE OF VARCHAR2(50);
    v_pozitive t_word_list := t_word_list(
        'bun', 'buna', 'buni', 'bune',
        'excelent', 'excelenta', 'excelenti', 'excelente',
        'amazing', 'super', 'superb', 'superba',
        'placut', 'placuta', 'placuti', 'placute',
        'impresionant', 'impresionanta', 'util', 'utila',
        'recomand', 'recomandat', 'multumit', 'multumita',
        'perfect', 'perfecta', 'minunat', 'minunata', 'ok'
    );
    v_negative t_word_list := t_word_list(
        'prost', 'proasta', 'prosti', 'proaste',
        'greu', 'grea', 'grele',
        'bad', 'slab', 'slaba', 'slabi', 'slabe',
        'plictisitor', 'plictisitoare', 'urata', 'urat',
        'groaznic', 'groaznica', 'oribil', 'oribila',
        'defect', 'defecta', 'probleme', 'eroare',
        'nemultumit', 'nemultumita', 'evitati', 'nasol', 'nasoala'
    );
BEGIN
    v_text := ' ' || LOWER(
                  TRANSLATE(p_text,
                            '.,!?;:', ' ')
              ) || ' ';
    FOR i IN 1..v_pozitive.COUNT LOOP
        IF INSTR(v_text, ' ' || LOWER(v_pozitive(i)) || ' ') > 0 THEN
            v_pos := v_pos + 1;
        END IF;
    END LOOP;
    FOR i IN 1..v_negative.COUNT LOOP
        IF INSTR(v_text, ' ' || LOWER(v_negative(i)) || ' ') > 0 THEN
            v_neg := v_neg + 1;
        END IF;
    END LOOP;
    IF v_pos > v_neg THEN RETURN 'pozitiv';
    ELSIF v_neg > v_pos THEN RETURN 'negativ';
    ELSE RETURN 'neutru';
    END IF;
END fnc_sentiment;
/

CREATE OR REPLACE FUNCTION fnc_categorie_preferata(p_id_client IN NUMBER) RETURN VARCHAR2 IS
    v_categorie VARCHAR2(50);
BEGIN
    SELECT categorie INTO v_categorie
    FROM (
        SELECT f.categorie, 
               SUM(1 + NVL((SELECT (v.punctaj - 3) * 2 FROM voturi v WHERE v.id_client = p_id_client AND v.id_film = f.id_film), 0)
                   + NVL((SELECT CASE fnc_sentiment(c.text_comentariu) WHEN 'pozitiv' THEN 3  WHEN 'negativ' THEN -10  ELSE 0 END 
                   FROM comentarii c  WHERE c.id_client = p_id_client AND c.id_film = f.id_film AND ROWNUM = 1), 0)
               ) AS scor_afinitate
        FROM vizualizari viz JOIN versiuni_film vf ON vf.id_versiune = viz.id_versiune JOIN filme f ON f.id_film = vf.id_film
        WHERE viz.id_client = p_id_client GROUP BY f.categorie
        HAVING SUM(1 + NVL((SELECT (v.punctaj - 3) * 2 FROM voturi v WHERE v.id_client = p_id_client AND v.id_film = f.id_film), 0)
                   + NVL((SELECT CASE fnc_sentiment(c.text_comentariu) WHEN 'pozitiv' THEN 3 WHEN 'negativ' THEN -10 ELSE 0 END 
                    FROM comentarii c WHERE c.id_client = p_id_client AND c.id_film = f.id_film AND ROWNUM = 1), 0)) > 0
        ORDER BY scor_afinitate DESC
    )
    WHERE ROWNUM = 1;

    RETURN v_categorie;
EXCEPTION
    WHEN NO_DATA_FOUND THEN 
        RETURN NULL;
END fnc_categorie_preferata;
/
CREATE OR REPLACE FUNCTION fnc_actori_frecventi(p_id_client IN NUMBER) 
RETURN SYS_REFCURSOR IS
    rc SYS_REFCURSOR;
BEGIN
    OPEN rc FOR
        SELECT a.nume_scena,
               COUNT(*) AS nr_vizionari_cu_actor
        FROM vizualizari viz
        JOIN versiuni_film vf ON vf.id_versiune = viz.id_versiune
        JOIN distributie_film df ON df.id_film  = vf.id_film
        JOIN actori a ON a.id_actor  = df.id_actor
        WHERE viz.id_client = p_id_client
        GROUP BY a.nume_scena
        ORDER BY nr_vizionari_cu_actor DESC;
    RETURN rc;
END fnc_actori_frecventi;
/