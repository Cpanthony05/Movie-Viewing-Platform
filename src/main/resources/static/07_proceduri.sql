CREATE OR REPLACE PROCEDURE proc_genereaza_recomandari(
    p_id_client IN  NUMBER,
    p_numar     IN  NUMBER DEFAULT 5,
    p_cursor    OUT SYS_REFCURSOR
) IS
    v_categorie_preferata VARCHAR2(50);
BEGIN
    v_categorie_preferata := fnc_categorie_preferata(p_id_client);

    OPEN p_cursor FOR
        SELECT * FROM (
            SELECT f.id_film, f.titlu, f.rating, f.categorie
            FROM   filme f
            WHERE  f.categorie = v_categorie_preferata
              AND  f.id_film NOT IN (
                       SELECT vf.id_film
                       FROM   vizualizari   viz
                       JOIN   versiuni_film vf ON vf.id_versiune = viz.id_versiune
                       WHERE  viz.id_client = p_id_client
                   )
            ORDER BY f.rating DESC
        )
        WHERE ROWNUM <= p_numar;
END proc_genereaza_recomandari;
/