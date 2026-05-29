CREATE INDEX idx_viz_client ON vizualizari(id_client);
CREATE INDEX idx_viz_versiune ON vizualizari(id_versiune);
CREATE INDEX idx_com_film ON comentarii(id_film);
CREATE INDEX idx_com_actor ON comentarii(id_actor);
CREATE INDEX idx_vers_film ON versiuni_film(id_film);
CREATE INDEX idx_filme_cat ON filme(categorie);
CREATE INDEX idx_actor_nume ON actori(nume_scena);
CREATE INDEX idx_viz_stare ON vizualizari(stare);