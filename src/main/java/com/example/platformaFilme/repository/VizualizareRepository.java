package com.example.platformaFilme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.platformaFilme.model.Vizualizare;

@Repository
public class VizualizareRepository {

    private final JdbcTemplate jdbc;

    public VizualizareRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Vizualizare> rowMapper = (rs, rowNum) -> {
        Vizualizare v = new Vizualizare();
        v.setIdVizualizare(rs.getLong("id_vizualizare"));
        v.setIdClient(rs.getLong("id_client"));
        v.setIdVersiune(rs.getLong("id_versiune"));
        java.sql.Date d = rs.getDate("data_vizualizare");
        if (d != null) v.setDataVizualizare(d.toLocalDate());
        v.setDurataMin(rs.getObject("durata_min", Integer.class));
        v.setStare(rs.getString("stare"));
        try { v.setNumeClient(rs.getString("nume_client")); } catch (Exception ignored) {}
        try { v.setTitluFilm(rs.getString("titlu")); } catch (Exception ignored) {}
        return v;
    };

    public List<Vizualizare> findAll() {
        return jdbc.query(
            "SELECT viz.*, c.nume || ' ' || c.prenume AS nume_client, f.titlu " +
            "FROM vizualizari viz " +
            "JOIN clienti c ON c.id_client = viz.id_client " +
            "JOIN versiuni_film vf ON vf.id_versiune = viz.id_versiune " +
            "JOIN filme f ON f.id_film = vf.id_film " +
            "ORDER BY viz.id_vizualizare",
            rowMapper);
    }

    public Optional<Vizualizare> findById(Long id) {
        List<Vizualizare> list = jdbc.query(
            "SELECT viz.*, c.nume || ' ' || c.prenume AS nume_client, f.titlu " +
            "FROM vizualizari viz " +
            "JOIN clienti c ON c.id_client = viz.id_client " +
            "JOIN versiuni_film vf ON vf.id_versiune = viz.id_versiune " +
            "JOIN filme f ON f.id_film = vf.id_film " +
            "WHERE viz.id_vizualizare = ?",
            rowMapper, id);
        return list.stream().findFirst();
    }

    public void insert(Vizualizare v) {
        jdbc.update(
            "INSERT INTO vizualizari (id_vizualizare, id_client, id_versiune, data_vizualizare, durata_min, stare) VALUES (seq_vizualizare.NEXTVAL, ?, ?, ?, ?, ?)",
            v.getIdClient(), v.getIdVersiune(),
            v.getDataVizualizare() != null ? java.sql.Date.valueOf(v.getDataVizualizare()) : new java.sql.Date(System.currentTimeMillis()),
            v.getDurataMin(), v.getStare()
        );
    }

    public void update(Vizualizare v) {
        jdbc.update(
            "UPDATE vizualizari SET id_client=?, id_versiune=?, data_vizualizare=?, durata_min=?, stare=? WHERE id_vizualizare=?",
            v.getIdClient(), v.getIdVersiune(),
            v.getDataVizualizare() != null ? java.sql.Date.valueOf(v.getDataVizualizare()) : null,
            v.getDurataMin(), v.getStare(), v.getIdVizualizare()
        );
    }

    public void delete(Long id) {
        jdbc.update("DELETE FROM vizualizari WHERE id_vizualizare = ?", id);
    }
}