CREATE TABLE filme (
    id_film NUMBER PRIMARY KEY,
    titlu VARCHAR2(200) NOT NULL,
    descriere CLOB,
    categorie VARCHAR2(50),
    data_lansare DATE,
    rating NUMBER(3,2) DEFAULT 0
);
CREATE TABLE versiuni_film (
    id_versiune NUMBER PRIMARY KEY,
    id_film NUMBER NOT NULL,
    format VARCHAR2(30),   
    rezolutie VARCHAR2(20),   
    limba VARCHAR2(30),   
    subtitrare VARCHAR2(30),
    CONSTRAINT fk_versiune_film FOREIGN KEY (id_film) REFERENCES filme(id_film)
);
CREATE TABLE clienti (
    id_client NUMBER PRIMARY KEY,
    nume VARCHAR2(50) NOT NULL,
    prenume VARCHAR2(50) NOT NULL,
    telefon_acasa VARCHAR2(20),
    adresa VARCHAR2(200),
    oras VARCHAR2(100),
    email VARCHAR2(100),
    telefon_mobil VARCHAR2(20)
);
CREATE TABLE actori (
    id_actor NUMBER PRIMARY KEY,
    nume_scena VARCHAR2(100) NOT NULL,
    prenume VARCHAR2(50),
    nume_familie VARCHAR2(50),
    data_nasterii DATE
);
CREATE TABLE distributie_film (
    id_film  NUMBER NOT NULL,
    id_actor NUMBER NOT NULL,
    rol VARCHAR2(100),
    CONSTRAINT pk_distributie PRIMARY KEY (id_film, id_actor),
    CONSTRAINT fk_dist_film FOREIGN KEY (id_film)  REFERENCES filme(id_film),
    CONSTRAINT fk_dist_actor FOREIGN KEY (id_actor) REFERENCES actori(id_actor)
);
CREATE TABLE vizualizari (
    id_vizualizare NUMBER PRIMARY KEY,
    id_client NUMBER NOT NULL,
    id_versiune NUMBER NOT NULL,
    data_vizualizare DATE NOT NULL,
    durata_min NUMBER,
    stare VARCHAR2(20), 
    CONSTRAINT fk_viz_client FOREIGN KEY (id_client) REFERENCES clienti(id_client),
    CONSTRAINT fk_viz_versiune FOREIGN KEY (id_versiune) REFERENCES versiuni_film(id_versiune)
);
CREATE TABLE voturi (
    id_vot    NUMBER PRIMARY KEY,
    id_client NUMBER NOT NULL,
    id_film   NUMBER NOT NULL,
    punctaj   NUMBER(1) CHECK (punctaj BETWEEN 1 AND 5),
    data_vot  DATE DEFAULT SYSDATE,
    CONSTRAINT fk_vot_client FOREIGN KEY (id_client) REFERENCES clienti(id_client),
    CONSTRAINT fk_vot_film FOREIGN KEY (id_film) REFERENCES filme(id_film)
);
CREATE TABLE comentarii (
    id_comentariu NUMBER PRIMARY KEY,
    id_client NUMBER NOT NULL,
    id_film NUMBER,           
    id_actor NUMBER,           
    text_comentariu CLOB,
    data_comentariu DATE DEFAULT SYSDATE,
    CONSTRAINT ck_comentariu_film_actor CHECK (
        (id_film IS NOT NULL AND id_actor IS NULL) OR
        (id_film IS NULL AND id_actor IS NOT NULL)
    ),
    CONSTRAINT fk_com_client FOREIGN KEY (id_client) REFERENCES clienti(id_client),
    CONSTRAINT fk_com_film   FOREIGN KEY (id_film)   REFERENCES filme(id_film),
    CONSTRAINT fk_com_actor  FOREIGN KEY (id_actor)  REFERENCES actori(id_actor)
);
CREATE TABLE optiuni_feedback (
    id_optiune NUMBER PRIMARY KEY,
    descriere VARCHAR2(100) NOT NULL
);
CREATE TABLE feedback_selectat (
    id_feedback NUMBER PRIMARY KEY,
    id_client NUMBER NOT NULL,
    id_film NUMBER NOT NULL,
    id_optiune NUMBER NOT NULL,
    data_feedback DATE DEFAULT SYSDATE,
    CONSTRAINT fk_fb_client FOREIGN KEY (id_client) REFERENCES clienti(id_client),
    CONSTRAINT fk_fb_film FOREIGN KEY (id_film) REFERENCES filme(id_film),
    CONSTRAINT fk_fb_optiune FOREIGN KEY (id_optiune) REFERENCES optiuni_feedback(id_optiune)
);