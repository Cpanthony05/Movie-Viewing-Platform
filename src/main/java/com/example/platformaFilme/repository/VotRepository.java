package com.example.platformaFilme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.platformaFilme.model.Vot;

@Repository
public class VotRepository {

    private final JdbcTemplate jdbc;

    public VotRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Vot> rowMapper = (rs, rowNum) -> {
        Vot v = new Vot();
        v.setIdVot(rs.getLong("id_vot"));
        v.setIdClient(rs.getLong("id_client"));
        v.setIdFilm(rs.getLong("id_film"));
        v.setPunctaj(rs.getObject("punctaj", Integer.class));
        java.sql.Date d = rs.getDate("data_vot");
        if (d != null) v.setDataVot(d.toLocalDate());
        try { v.setNumeClient(rs.getString("nume_client")); } catch (Exception ignored) {}
        try { v.setTitluFilm(rs.getString("titlu")); } catch (Exception ignored) {}
        return v;
    };

    public List<Vot> findAll() {
        return jdbc.query(
            "SELECT vt.*, c.nume || ' ' || c.prenume AS nume_client, f.titlu " +
            "FROM voturi vt " +
            "JOIN clienti c ON c.id_client = vt.id_client " +
            "JOIN filme f ON f.id_film = vt.id_film " +
            "ORDER BY vt.id_vot",
            rowMapper);
    }

    public Optional<Vot> findById(Long id) {
        List<Vot> list = jdbc.query(
            "SELECT vt.*, c.nume || ' ' || c.prenume AS nume_client, f.titlu " +
            "FROM voturi vt " +
            "JOIN clienti c ON c.id_client = vt.id_client " +
            "JOIN filme f ON f.id_film = vt.id_film " +
            "WHERE vt.id_vot = ?",
            rowMapper, id);
        return list.stream().findFirst();
    }

    public void insert(Vot v) {
        jdbc.update(
            "INSERT INTO voturi (id_vot, id_client, id_film, punctaj) VALUES (seq_vot.NEXTVAL, ?, ?, ?)",
            v.getIdClient(), v.getIdFilm(), v.getPunctaj()
        );
    }

    public void update(Vot v) {
        jdbc.update(
            "UPDATE voturi SET punctaj=? WHERE id_vot=?",
            v.getPunctaj(), v.getIdVot()
        );
    }

    public void delete(Long id) {
        jdbc.update("DELETE FROM voturi WHERE id_vot = ?", id);
    }
}