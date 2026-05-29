package com.example.platformaFilme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.platformaFilme.model.Actor;

@Repository
public class ActorRepository {

    private final JdbcTemplate jdbc;

    public ActorRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Actor> rowMapper = (rs, rowNum) -> {
        Actor a = new Actor();
        a.setIdActor(rs.getLong("id_actor"));
        a.setNumeScena(rs.getString("nume_scena"));
        a.setPrenume(rs.getString("prenume"));
        a.setNumeFamilie(rs.getString("nume_familie"));
        java.sql.Date d = rs.getDate("data_nasterii");
        if (d != null) a.setDataNasterii(d.toLocalDate());
        return a;
    };

    public List<Actor> findAll() {
        return jdbc.query("SELECT * FROM actori ORDER BY id_actor", rowMapper);
    }

    public Optional<Actor> findById(Long id) {
        List<Actor> list = jdbc.query("SELECT * FROM actori WHERE id_actor = ?", rowMapper, id);
        return list.stream().findFirst();
    }

    public void insert(Actor a) {
        jdbc.update(
            "INSERT INTO actori (id_actor, nume_scena, prenume, nume_familie, data_nasterii) VALUES (seq_actor.NEXTVAL, ?, ?, ?, ?)",
            a.getNumeScena(), a.getPrenume(), a.getNumeFamilie(),
            a.getDataNasterii() != null ? java.sql.Date.valueOf(a.getDataNasterii()) : null
        );
    }

    public void update(Actor a) {
        jdbc.update(
            "UPDATE actori SET nume_scena=?, prenume=?, nume_familie=?, data_nasterii=? WHERE id_actor=?",
            a.getNumeScena(), a.getPrenume(), a.getNumeFamilie(),
            a.getDataNasterii() != null ? java.sql.Date.valueOf(a.getDataNasterii()) : null,
            a.getIdActor()
        );
    }

    public void delete(Long id) {
        jdbc.update("DELETE FROM actori WHERE id_actor = ?", id);
    }
}