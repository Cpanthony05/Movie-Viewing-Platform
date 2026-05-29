DROP INDEX idx_viz_client;
DROP INDEX idx_viz_versiune;
DROP INDEX idx_com_film;
DROP INDEX idx_com_actor;
DROP INDEX idx_vers_film;
DROP INDEX idx_filme_cat;
DROP INDEX idx_actor_nume;
DROP INDEX idx_viz_stare;

DROP PACKAGE pkg_statistici_recomandari;
DROP TRIGGER trg_actualizeaza_rating;
DROP TRIGGER trg_verifica_vot;
DROP FUNCTION fnc_sentiment;
DROP FUNCTION fnc_actori_frecventi;
DROP FUNCTION fnc_categorie_preferata;
DROP PROCEDURE proc_adauga_vot;
DROP PROCEDURE proc_adauga_feedback;
DROP PROCEDURE proc_genereaza_recomandari;

DROP VIEW v_profil_client;
DROP SEQUENCE seq_film;
DROP SEQUENCE seq_versiune_film;
DROP SEQUENCE seq_client;
DROP SEQUENCE seq_actor;
DROP SEQUENCE seq_vizualizare;
DROP SEQUENCE seq_vot;
DROP SEQUENCE seq_comentariu;
DROP SEQUENCE seq_feedback;

DROP TABLE feedback_selectat CASCADE CONSTRAINTS;
DROP TABLE optiuni_feedback  CASCADE CONSTRAINTS;
DROP TABLE comentarii        CASCADE CONSTRAINTS;
DROP TABLE voturi            CASCADE CONSTRAINTS;
DROP TABLE vizualizari       CASCADE CONSTRAINTS;
DROP TABLE distributie_film  CASCADE CONSTRAINTS;
DROP TABLE versiuni_film     CASCADE CONSTRAINTS;
DROP TABLE actori            CASCADE CONSTRAINTS;
DROP TABLE clienti           CASCADE CONSTRAINTS;
DROP TABLE filme             CASCADE CONSTRAINTS;
