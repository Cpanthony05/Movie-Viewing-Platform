CREATE OR REPLACE TRIGGER trg_actualizeaza_rating
AFTER INSERT OR UPDATE OR DELETE ON voturi
BEGIN
    UPDATE filme f
    SET f.rating = (
               SELECT ROUND(AVG(v.punctaj), 2)
               FROM voturi v
               WHERE v.id_film = f.id_film
           )
    WHERE f.id_film IN (SELECT DISTINCT id_film FROM voturi);
END;
/

CREATE OR REPLACE TRIGGER trg_verifica_vot
BEFORE INSERT ON voturi
FOR EACH ROW
DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM vizualizari viz
    JOIN versiuni_film vf ON vf.id_versiune = viz.id_versiune
    WHERE viz.id_client = :NEW.id_client
      AND vf.id_film = :NEW.id_film
      AND viz.stare = 'finalizat';

    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20011,
            'Clientul trebuie sa fi vizionat filmul complet pentru a putea vota.');
    END IF;
END;
/