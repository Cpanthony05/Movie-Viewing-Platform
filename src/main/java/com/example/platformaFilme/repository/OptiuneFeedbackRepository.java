package com.example.platformaFilme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.platformaFilme.model.OptiuneFeedback;

@Repository
public class OptiuneFeedbackRepository {

    private final JdbcTemplate jdbc;

    public OptiuneFeedbackRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<OptiuneFeedback> rowMapper = (rs, rowNum) -> {
        OptiuneFeedback o = new OptiuneFeedback();
        o.setIdOptiune(rs.getLong("id_optiune"));
        o.setDescriere(rs.getString("descriere"));
        return o;
    };

    public List<OptiuneFeedback> findAll() {
        return jdbc.query("SELECT * FROM optiuni_feedback ORDER BY id_optiune", rowMapper);
    }

    public Optional<OptiuneFeedback> findById(Long id) {
        List<OptiuneFeedback> list = jdbc.query("SELECT * FROM optiuni_feedback WHERE id_optiune = ?", rowMapper, id);
        return list.stream().findFirst();
    }

    public void insert(OptiuneFeedback o) {
        jdbc.update(
            "INSERT INTO optiuni_feedback (id_optiune, descriere) VALUES ((SELECT NVL(MAX(id_optiune),0)+1 FROM optiuni_feedback), ?)",
            o.getDescriere()
        );
    }

    public void update(OptiuneFeedback o) {
        jdbc.update("UPDATE optiuni_feedback SET descriere=? WHERE id_optiune=?", o.getDescriere(), o.getIdOptiune());
    }

    public void delete(Long id) {
        jdbc.update("DELETE FROM optiuni_feedback WHERE id_optiune = ?", id);
    }
}