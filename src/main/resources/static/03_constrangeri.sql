ALTER TABLE clienti ADD CONSTRAINT uq_email UNIQUE (email);
ALTER TABLE filme ADD CONSTRAINT uq_titlu UNIQUE (titlu);
ALTER TABLE optiuni_feedback ADD CONSTRAINT uq_descriere UNIQUE (descriere);
ALTER TABLE voturi ADD CONSTRAINT uq_client_film_vot UNIQUE (id_client, id_film);