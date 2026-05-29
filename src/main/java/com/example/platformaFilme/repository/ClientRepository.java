package com.example.platformaFilme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.platformaFilme.model.Client;

@Repository
public class ClientRepository {

    private final JdbcTemplate jdbc;

    public ClientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Client> rowMapper = (rs, rowNum) -> {
        Client c = new Client();
        c.setIdClient(rs.getLong("id_client"));
        c.setNume(rs.getString("nume"));
        c.setPrenume(rs.getString("prenume"));
        c.setTelefonAcasa(rs.getString("telefon_acasa"));
        c.setAdresa(rs.getString("adresa"));
        c.setOras(rs.getString("oras"));
        c.setEmail(rs.getString("email"));
        c.setTelefonMobil(rs.getString("telefon_mobil"));
        return c;
    };

    public List<Client> findAll() {
        return jdbc.query("SELECT * FROM clienti ORDER BY id_client", rowMapper);
    }

    public Optional<Client> findById(Long id) {
        List<Client> list = jdbc.query("SELECT * FROM clienti WHERE id_client = ?", rowMapper, id);
        return list.stream().findFirst();
    }

    public void insert(Client c) {
        jdbc.update(
            "INSERT INTO clienti (id_client, nume, prenume, telefon_acasa, adresa, oras, email, telefon_mobil) VALUES (seq_client.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)",
            c.getNume(), c.getPrenume(), c.getTelefonAcasa(), c.getAdresa(), c.getOras(), c.getEmail(), c.getTelefonMobil()
        );
    }

    public void update(Client c) {
        jdbc.update(
            "UPDATE clienti SET nume=?, prenume=?, telefon_acasa=?, adresa=?, oras=?, email=?, telefon_mobil=? WHERE id_client=?",
            c.getNume(), c.getPrenume(), c.getTelefonAcasa(), c.getAdresa(), c.getOras(), c.getEmail(), c.getTelefonMobil(), c.getIdClient()
        );
    }

    public void delete(Long id) {
        jdbc.update("DELETE FROM clienti WHERE id_client = ?", id);
    }
}