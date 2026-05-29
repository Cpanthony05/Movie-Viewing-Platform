package com.example.platformaFilme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.platformaFilme.model.Comentariu;

@Repository
public class ComentariuRepository {

    private final JdbcTemplate jdbc;

    public ComentariuRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Comentariu> rowMapper = (rs, rowNum) -> {
        Comentariu c = new Comentariu();
        c.setIdComentariu(rs.getLong("id_comentariu"));
        c.setIdClient(rs.getLong("id_client"));
        long idFilm = rs.getLong("id_film");
        if (!rs.wasNull()) c.setIdFilm(idFilm);
        long idActor = rs.getLong("id_actor");
        if (!rs.wasNull()) c.setIdActor(idActor);
        c.setTextComentariu(rs.getString("text_comentariu"));
        java.sql.Date d = rs.getDate("data_comentariu");
        if (d != null) c.setDataComentariu(d.toLocalDate());
        try { c.setNumeClient(rs.getString("nume_client")); } catch (Exception ignored) {}
        try { c.setTitluFilm(rs.getString("titlu_film")); } catch (Exception ignored) {}
        try { c.setNumeActor(rs.getString("nume_scena")); } catch (Exception ignored) {}
        return c;
    };

    public List<Comentariu> findAll() {
        return jdbc.query(
            "SELECT com.*, c.nume || ' ' || c.prenume AS nume_client, f.titlu AS titlu_film, a.nume_scena " +
            "FROM comentarii com " +
            "JOIN clienti c ON c.id_client = com.id_client " +
            "LEFT JOIN filme f ON f.id_film = com.id_film " +
            "LEFT JOIN actori a ON a.id_actor = com.id_actor " +
            "ORDER BY com.id_comentariu",
            rowMapper);
    }

    public Optional<Comentariu> findById(Long id) {
        List<Comentariu> list = jdbc.query(
            "SELECT com.*, c.nume || ' ' || c.prenume AS nume_client, f.titlu AS titlu_film, a.nume_scena " +
            "FROM comentarii com " +
            "JOIN clienti c ON c.id_client = com.id_client " +
            "LEFT JOIN filme f ON f.id_film = com.id_film " +
            "LEFT JOIN actori a ON a.id_actor = com.id_actor " +
            "WHERE com.id_comentariu = ?",
            rowMapper, id);
        return list.stream().findFirst();
    }

    public void insert(Comentariu c) {
        jdbc.update(
            "INSERT INTO comentarii (id_comentariu, id_client, id_film, id_actor, text_comentariu) VALUES (seq_comentariu.NEXTVAL, ?, ?, ?, ?)",
            c.getIdClient(), c.getIdFilm(), c.getIdActor(), c.getTextComentariu()
        );
    }

    public void update(Comentariu c) {
        jdbc.update(
            "UPDATE comentarii SET id_client=?, id_film=?, id_actor=?, text_comentariu=? WHERE id_comentariu=?",
            c.getIdClient(), c.getIdFilm(), c.getIdActor(), c.getTextComentariu(), c.getIdComentariu()
        );
    }

    public void delete(Long id) {
        jdbc.update("DELETE FROM comentarii WHERE id_comentariu = ?", id);
    }
}