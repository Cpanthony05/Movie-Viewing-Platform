package com.example.platformaFilme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.platformaFilme.model.VersiuneFilm;

@Repository
public class VersiuneFilmRepository {

    private final JdbcTemplate jdbc;

    public VersiuneFilmRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<VersiuneFilm> rowMapper = (rs, rowNum) -> {
        VersiuneFilm v = new VersiuneFilm();
        v.setIdVersiune(rs.getLong("id_versiune"));
        v.setIdFilm(rs.getLong("id_film"));
        v.setFormat(rs.getString("format"));
        v.setRezolutie(rs.getString("rezolutie"));
        v.setLimba(rs.getString("limba"));
        v.setSubtitrare(rs.getString("subtitrare"));
        try { v.setTitluFilm(rs.getString("titlu")); } catch (Exception ignored) {}
        return v;
    };

    public List<VersiuneFilm> findAll() {
        return jdbc.query(
            "SELECT vf.*, f.titlu FROM versiuni_film vf JOIN filme f ON f.id_film = vf.id_film ORDER BY vf.id_versiune",
            rowMapper);
    }

    public Optional<VersiuneFilm> findById(Long id) {
        List<VersiuneFilm> list = jdbc.query(
            "SELECT vf.*, f.titlu FROM versiuni_film vf JOIN filme f ON f.id_film = vf.id_film WHERE vf.id_versiune = ?",
            rowMapper, id);
        return list.stream().findFirst();
    }

    public List<VersiuneFilm> findByFilmId(Long idFilm) {
        return jdbc.query(
            "SELECT vf.*, f.titlu FROM versiuni_film vf JOIN filme f ON f.id_film = vf.id_film WHERE vf.id_film = ? ORDER BY vf.id_versiune",
            rowMapper, idFilm);
    }

    public void insert(VersiuneFilm v) {
        jdbc.update(
            "INSERT INTO versiuni_film (id_versiune, id_film, format, rezolutie, limba, subtitrare) VALUES (seq_versiune_film.NEXTVAL, ?, ?, ?, ?, ?)",
            v.getIdFilm(), v.getFormat(), v.getRezolutie(), v.getLimba(), v.getSubtitrare()
        );
    }

    public void update(VersiuneFilm v) {
        jdbc.update(
            "UPDATE versiuni_film SET id_film=?, format=?, rezolutie=?, limba=?, subtitrare=? WHERE id_versiune=?",
            v.getIdFilm(), v.getFormat(), v.getRezolutie(), v.getLimba(), v.getSubtitrare(), v.getIdVersiune()
        );
    }

    public void delete(Long id) {
        jdbc.update("DELETE FROM versiuni_film WHERE id_versiune = ?", id);
    }
}