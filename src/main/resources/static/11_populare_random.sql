SET SERVEROUTPUT ON;

DECLARE
    TYPE t_array IS TABLE OF VARCHAR2(200);
    v_prenume t_array := t_array('Ion', 'Maria', 'Andrei', 'Elena', 'Vasile', 'Ana', 'Gheorghe', 'Mihaela', 'Stefan', 'Roxana', 'David', 'Carmen', 'Adrian', 'Iulia', 'Victor', 'Alexandru', 'Cristina', 'Mihai', 'Laura', 'Gabriel');
    v_nume t_array := t_array('Popescu', 'Ionescu', 'Radu', 'Dumitru', 'Stoica', 'Stan', 'Gheorghe', 'Rusu', 'Matei', 'Marin', 'Lupu', 'Moldovan', 'Simion', 'Ionita', 'Vasile', 'Dobre', 'Barbu', 'Nistor', 'Florea', 'Ene');
    v_tit_p t_array := t_array('Misterul', 'Ultimul', 'Legenda', 'Zborul', 'Secretul', 'Umbra', 'Destinul', 'Intoarcerea', 'Blestemul', 'Orasul', 'Padurea', 'Inima', 'Cantecul', 'Lumina', 'Cronica');
    v_tit_s t_array := t_array('Lunii', 'Desertului', 'Noptii', 'Regelui', 'Eroului', 'Timpului', 'Uitat', 'Pierdut', 'Fara Sfarsit', 'de Smarald', 'de Foc', 'Subestimat', 'Sperantei', 'Ghetii', 'Stelelor');
    v_desc t_array := t_array('O poveste captivanta despre curaj si onoare.', 'Un thriller psihologic intens cu rasturnari de situatie.', 'O comedie savuroasa pentru intreaga familie.', 'Documentar detaliat despre viata in salbaticie.', 'O aventura epica intr-un viitor distopic.', 'Drama emotionanta bazata pe fapte reale.', 'Animatie plina de culoare si invataminte.');
    
    v_categorii t_array := t_array('Drama', 'Actiune', 'Comedie', 'SF', 'Horror', 'Documentar', 'Animatie', 'Thriller', 'Aventura', 'Romantic');
    
    v_com_poz t_array := t_array('un film bun si excelent', 'este super superba experienta', 'foarte placut si impresionant', 'o experienta minunata este ok', 'perfect realizat foarte util');
    v_com_neg t_array := t_array('slab si plictisitor', 'oribil o experienta nasoala', 'groaznic am avut probleme', 'prost si urat nu recomand', 'evitati acest film este nasol');
    v_optiuni t_array := t_array('Imagine superba', 'Sunet clar', 'Actori talentati', 'Scenariu slab', 'Efecte speciale uimitoare', 'Coloana sonora deosebita', 'Regie impecabila', 'Subtitrare eronata', 'Prea lung', 'Final previzibil', 'Originalitate crescuta', 'Emotie pura', 'Dialoguri inteligente', 'Cadre spectaculoase', 'Ritmu alert');
    
    TYPE t_ids IS TABLE OF NUMBER;
    v_f_ids t_ids := t_ids(); v_c_ids t_ids := t_ids(); v_a_ids t_ids := t_ids(); v_v_ids t_ids := t_ids(); v_o_ids t_ids := t_ids();

    PROCEDURE shuffle_array(p_array IN OUT t_array) IS
        v_temp VARCHAR2(200);
        v_idx  NUMBER;
    BEGIN
        FOR i IN REVERSE 2..p_array.COUNT LOOP
            v_idx := TRUNC(DBMS_RANDOM.VALUE(1, i + 1));
            v_temp := p_array(v_idx);
            p_array(v_idx) := p_array(i);
            p_array(i) := v_temp;
        END LOOP;
    END;
BEGIN
    shuffle_array(v_prenume);
    shuffle_array(v_nume);
    shuffle_array(v_tit_p);
    shuffle_array(v_tit_s);
    shuffle_array(v_desc);
    shuffle_array(v_optiuni);
    shuffle_array(v_categorii);

    FOR i IN 1..v_optiuni.COUNT LOOP
        INSERT INTO optiuni_feedback (id_optiune, descriere) 
        VALUES (seq_feedback.NEXTVAL, v_optiuni(i));
        v_o_ids.EXTEND; 
        v_o_ids(v_o_ids.LAST) := seq_feedback.CURRVAL;
    END LOOP;

    FOR i IN 1..25 LOOP
        INSERT INTO actori (id_actor, nume_scena, prenume, nume_familie, data_nasterii)
        VALUES (seq_actor.NEXTVAL, v_prenume(MOD(i, 20)+1)||' '||v_nume(MOD(i+5, 20)+1), v_prenume(MOD(i, 20)+1), v_nume(MOD(i+5, 20)+1), TO_DATE('1980-01-01','YYYY-MM-DD')+i);
        v_a_ids.EXTEND; 
        v_a_ids(v_a_ids.LAST) := seq_actor.CURRVAL;
    END LOOP;

    FOR i IN 1..25 LOOP
        INSERT INTO filme (id_film, titlu, descriere, categorie, data_lansare, rating)
        VALUES (
            seq_film.NEXTVAL, 
            v_tit_p(MOD(i, 15)+1)||' '||v_tit_s(MOD(i+2, 15)+1)||' '||i, 
            v_desc(MOD(i, 7)+1), 
            v_categorii(MOD(i, 10)+1), 
            SYSDATE-i, 
            0
        );
        
        v_f_ids.EXTEND; 
        v_f_ids(v_f_ids.LAST) := seq_film.CURRVAL;

        INSERT INTO versiuni_film (id_versiune, id_film, format, rezolutie, limba, subtitrare)
        VALUES (seq_versiune_film.NEXTVAL, seq_film.CURRVAL, 'Streaming', '4K', 'Romana', 'Engleza');
        
        v_v_ids.EXTEND; 
        v_v_ids(v_v_ids.LAST) := seq_versiune_film.CURRVAL;
    END LOOP;

    FOR i IN 1..25 LOOP
        INSERT INTO clienti (id_client, nume, prenume, telefon_acasa, adresa, oras, email, telefon_mobil)
        VALUES (seq_client.NEXTVAL, v_nume(MOD(i, 20)+1), v_prenume(MOD(i+2, 20)+1), '02100'||i, 'Strada '||i, 'Bucuresti', 'client'||i||'@cinema.ro', '07200'||i);
        v_c_ids.EXTEND; 
        v_c_ids(v_c_ids.LAST) := seq_client.CURRVAL;
    END LOOP;

    FOR i IN 1..v_f_ids.COUNT LOOP
        INSERT INTO distributie_film (id_film, id_actor, rol) VALUES (v_f_ids(i), v_a_ids(MOD(i, 25)+1), 'Principal');
        INSERT INTO distributie_film (id_film, id_actor, rol) VALUES (v_f_ids(i), v_a_ids(MOD(i+1, 25)+1), 'Secundar');
    END LOOP;

    FOR i IN 1..25 LOOP
        INSERT INTO vizualizari (id_vizualizare, id_client, id_versiune, data_vizualizare, durata_min, stare)
        VALUES (seq_vizualizare.NEXTVAL, v_c_ids(i), v_v_ids(i), SYSDATE, 120, 'finalizat');
        
        INSERT INTO voturi (id_vot, id_client, id_film, punctaj, data_vot)
        VALUES (seq_vot.NEXTVAL, v_c_ids(i), v_f_ids(i), CASE WHEN MOD(i, 2) = 0 THEN 5 ELSE 1 END, SYSDATE);

        INSERT INTO comentarii (id_comentariu, id_client, id_film, id_actor, text_comentariu, data_comentariu)
        VALUES (seq_comentariu.NEXTVAL, v_c_ids(i), v_f_ids(i), NULL, 
                CASE WHEN MOD(i, 2) = 0 THEN v_com_poz(MOD(i, 5)+1) ELSE v_com_neg(MOD(i, 5)+1) END, 
                SYSDATE);
        
        INSERT INTO feedback_selectat (id_feedback, id_client, id_film, id_optiune, data_feedback)
        VALUES (seq_feedback.NEXTVAL, v_c_ids(i), v_f_ids(i), v_o_ids(MOD(i, 15)+1), SYSDATE);
    END LOOP;

    FOR i IN 1..20 LOOP
        INSERT INTO comentarii (id_comentariu, id_client, id_film, id_actor, text_comentariu, data_comentariu)
        VALUES (seq_comentariu.NEXTVAL, v_c_ids(MOD(i, 25)+1), NULL, v_a_ids(MOD(i, 25)+1), 
                CASE WHEN MOD(i, 2) = 0 THEN 'un actor foarte bun' ELSE 'interpretare slaba si nasola' END, 
                SYSDATE);
    END LOOP;

    COMMIT;
END;
/