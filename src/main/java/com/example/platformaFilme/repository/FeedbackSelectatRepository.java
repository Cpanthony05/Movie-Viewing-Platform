
package com.example.platformaFilme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.platformaFilme.model.FeedbackSelectat;

@Repository
public class FeedbackSelectatRepository {

    private final JdbcTemplate jdbc;

    public FeedbackSelectatRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<FeedbackSelectat> rowMapper = (rs, rowNum) -> {
        FeedbackSelectat fs = new FeedbackSelectat();
        fs.setIdFeedback(rs.getLong("id_feedback"));
        fs.setIdClient(rs.getLong("id_client"));
        fs.setIdFilm(rs.getLong("id_film"));
        fs.setIdOptiune(rs.getLong("id_optiune"));
        java.sql.Date d = rs.getDate("data_feedback");
        if (d != null) fs.setDataFeedback(d.toLocalDate());
        try { fs.setNumeClient(rs.getString("nume_client")); } catch (Exception ignored) {}
        try { fs.setTitluFilm(rs.getString("titlu")); } catch (Exception ignored) {}
        try { fs.setDescriereOptiune(rs.getString("descriere")); } catch (Exception ignored) {}
        return fs;
    };

    public List<FeedbackSelectat> findAll() {
        return jdbc.query(
            "SELECT fs.*, c.nume || ' ' || c.prenume AS nume_client, f.titlu, opf.descriere " +
            "FROM feedback_selectat fs " +
            "JOIN clienti c ON c.id_client = fs.id_client " +
            "JOIN filme f ON f.id_film = fs.id_film " +
            "JOIN optiuni_feedback opf ON opf.id_optiune = fs.id_optiune " +
            "ORDER BY fs.id_feedback",
            rowMapper);
    }

    public Optional<FeedbackSelectat> findById(Long id) {
        List<FeedbackSelectat> list = jdbc.query(
            "SELECT fs.*, c.nume || ' ' || c.prenume AS nume_client, f.titlu, opf.descriere " +
            "FROM feedback_selectat fs " +
            "JOIN clienti c ON c.id_client = fs.id_client " +
            "JOIN filme f ON f.id_film = fs.id_film " +
            "JOIN optiuni_feedback opf ON opf.id_optiune = fs.id_optiune " +
            "WHERE fs.id_feedback = ?",
            rowMapper, id);
        return list.stream().findFirst();
    }

    public void insert(FeedbackSelectat fs) {
        jdbc.update(
            "INSERT INTO feedback_selectat (id_feedback, id_client, id_film, id_optiune) VALUES (seq_feedback.NEXTVAL, ?, ?, ?)",
            fs.getIdClient(), fs.getIdFilm(), fs.getIdOptiune()
        );
    }

    public void update(FeedbackSelectat fs) {
        jdbc.update(
            "UPDATE feedback_selectat SET id_client=?, id_film=?, id_optiune=? WHERE id_feedback=?",
            fs.getIdClient(), fs.getIdFilm(), fs.getIdOptiune(), fs.getIdFeedback()
        );
    }

    public void delete(Long id) {
        jdbc.update("DELETE FROM feedback_selectat WHERE id_feedback = ?", id);
    }
}