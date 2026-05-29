package com.example.platformaFilme.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.platformaFilme.model.DistributieFilm;

@Repository
public class DistributieFilmRepository {

    private final JdbcTemplate jdbc;

    public DistributieFilmRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<DistributieFilm> rowMapper = (rs, rowNum) -> {
        DistributieFilm d = new DistributieFilm();
        d.setIdFilm(rs.getLong("id_film"));
        d.setIdActor(rs.getLong("id_actor"));
        d.setRol(rs.getString("rol"));
        try { d.setTitluFilm(rs.getString("titlu")); } catch (Exception ignored) {}
        try { d.setNumeActor(rs.getString("nume_scena")); } catch (Exception ignored) {}
        return d;
    };

    public List<DistributieFilm> findAll() {
        return jdbc.query(
            "SELECT df.*, f.titlu, a.nume_scena FROM distributie_film df " +
            "JOIN filme f ON f.id_film = df.id_film " +
            "JOIN actori a ON a.id_actor = df.id_actor " +
            "ORDER BY df.id_film, df.id_actor",
            rowMapper);
    }

    public List<DistributieFilm> findByFilm(Long idFilm) {
        return jdbc.query(
            "SELECT df.*, f.titlu, a.nume_scena FROM distributie_film df " +
            "JOIN filme f ON f.id_film = df.id_film " +
            "JOIN actori a ON a.id_actor = df.id_actor " +
            "WHERE df.id_film = ? ORDER BY a.nume_scena",
            rowMapper, idFilm);
    }

    public void insert(DistributieFilm d) {
        jdbc.update(
            "INSERT INTO distributie_film (id_film, id_actor, rol) VALUES (?, ?, ?)",
            d.getIdFilm(), d.getIdActor(), d.getRol()
        );
    }

    public void update(Long idFilm, Long idActor, String rol) {
        jdbc.update(
            "UPDATE distributie_film SET rol=? WHERE id_film=? AND id_actor=?",
            rol, idFilm, idActor
        );
    }

    public void delete(Long idFilm, Long idActor) {
        jdbc.update("DELETE FROM distributie_film WHERE id_film=? AND id_actor=?", idFilm, idActor);
    }
}