CREATE OR REPLACE VIEW v_profil_client AS
SELECT
    c.id_client,
    c.nume || ' ' || c.prenume AS nume_complet,
    f.titlu AS film_vizionat,
    f.categorie,
    viz.data_vizualizare,
    viz.stare,
    vt.punctaj AS vot_acordat,
    com.text_comentariu,
    fnc_sentiment(com.text_comentariu) AS sentiment_comentariu,
    opf.descriere AS caracterizare_selectata
FROM clienti c
JOIN vizualizari viz ON viz.id_client = c.id_client
JOIN versiuni_film vf ON vf.id_versiune = viz.id_versiune
JOIN filme f ON f.id_film = vf.id_film
LEFT JOIN voturi vt ON vt.id_client = c.id_client AND vt.id_film = f.id_film
LEFT JOIN comentarii com ON com.id_client = c.id_client AND com.id_film = f.id_film
LEFT JOIN feedback_selectat fs ON fs.id_client = c.id_client AND fs.id_film = f.id_film
LEFT JOIN optiuni_feedback opf ON opf.id_optiune = fs.id_optiune;