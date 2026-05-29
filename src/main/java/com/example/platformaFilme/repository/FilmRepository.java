package com.example.platformaFilme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.platformaFilme.model.Film;

@Repository
public class FilmRepository {

    private final JdbcTemplate jdbc;

    public FilmRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Film> rowMapper = (rs, rowNum) -> {
        Film f = new Film();
        f.setIdFilm(rs.getLong("id_film"));
        f.setTitlu(rs.getString("titlu"));
        f.setDescriere(rs.getString("descriere"));
        f.setCategorie(rs.getString("categorie"));
        java.sql.Date d = rs.getDate("data_lansare");
        if (d != null) f.setDataLansare(d.toLocalDate());
        java.math.BigDecimal r = rs.getBigDecimal("rating");
        f.setRating(r);
        return f;
    };

    public List<Film> findAll() {
        return jdbc.query("SELECT * FROM filme ORDER BY id_film", rowMapper);
    }

    public Optional<Film> findById(Long id) {
        List<Film> list = jdbc.query("SELECT * FROM filme WHERE id_film = ?", rowMapper, id);
        return list.stream().findFirst();
    }

    public void insert(Film f) {
        jdbc.update(
            "INSERT INTO filme (id_film, titlu, descriere, categorie, data_lansare) VALUES (seq_film.NEXTVAL, ?, ?, ?, ?)",
            f.getTitlu(), f.getDescriere(), f.getCategorie(),
            f.getDataLansare() != null ? java.sql.Date.valueOf(f.getDataLansare()) : null
        );
    }

    public void update(Film f) {
        jdbc.update(
            "UPDATE filme SET titlu=?, descriere=?, categorie=?, data_lansare=? WHERE id_film=?",
            f.getTitlu(), f.getDescriere(), f.getCategorie(),
            f.getDataLansare() != null ? java.sql.Date.valueOf(f.getDataLansare()) : null,
            f.getIdFilm()
        );
    }

    public void delete(Long id) {
        jdbc.update("DELETE FROM filme WHERE id_film = ?", id);
    }
}