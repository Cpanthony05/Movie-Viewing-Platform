package com.example.platformaFilme.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.platformaFilme.model.ProfilClient;
import com.example.platformaFilme.model.RecomandareFilm;

import oracle.jdbc.OracleTypes;

@Repository
public class StatisticiRepository {

    private final JdbcTemplate jdbc;
    private final DataSource dataSource;

    public StatisticiRepository(JdbcTemplate jdbc, DataSource dataSource) {
        this.jdbc = jdbc;
        this.dataSource = dataSource;
    }

    public List<RecomandareFilm> genereazaRecomandari(Long idClient, int numar) {
        List<RecomandareFilm> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             CallableStatement cs = conn.prepareCall("{ CALL proc_genereaza_recomandari(?, ?, ?) }")) {
            cs.setLong(1, idClient);
            cs.setInt(2, numar);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();
            try (ResultSet rs = (ResultSet) cs.getObject(3)) {
                while (rs.next()) {
                    RecomandareFilm r = new RecomandareFilm();
                    r.setIdFilm(rs.getLong("id_film"));
                    r.setTitlu(rs.getString("titlu"));
                    r.setRating(rs.getBigDecimal("rating"));
                    r.setCategorie(rs.getString("categorie"));
                    result.add(r);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la proc_genereaza_recomandari: " + e.getMessage(), e);
        }
        return result;
    }

    public List<String[]> actoriFrecventi(Long idClient) {
    List<String[]> result = new ArrayList<>();
    try (Connection conn = dataSource.getConnection();
         CallableStatement cs = conn.prepareCall("{ ? = CALL fnc_actori_frecventi(?) }")) {
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        cs.setLong(2, idClient);
        cs.execute();
        try (ResultSet rs = (ResultSet) cs.getObject(1)) {
            while (rs.next()) {
                result.add(new String[]{
                    rs.getString("nume_scena"),
                    String.valueOf(rs.getInt("nr_vizionari_cu_actor"))
                });
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Eroare la actori_frecventi: " + e.getMessage(), e);
    }
    return result;
    }

    public List<ProfilClient> getProfilClientView(Long idClient) {
        String sql = "SELECT * FROM v_profil_client WHERE id_client = ? ORDER BY data_vizualizare DESC";
        return jdbc.query(sql, (rs, rowNum) -> {
            ProfilClient p = new ProfilClient();
            p.setIdClient(rs.getLong("id_client"));
            p.setNumeComplet(rs.getString("nume_complet"));
            p.setFilmVizionat(rs.getString("film_vizionat"));
            p.setCategorie(rs.getString("categorie"));
            java.sql.Date d = rs.getDate("data_vizualizare");
            if (d != null) p.setDataVizualizare(d.toLocalDate());
            p.setStare(rs.getString("stare"));
            int vot = rs.getInt("vot_acordat");
            if (!rs.wasNull()) p.setVotAcordat(vot);
            p.setTextComentariu(rs.getString("text_comentariu"));
            p.setSentimentComentariu(rs.getString("sentiment_comentariu"));
            p.setCaracterizareSelectata(rs.getString("caracterizare_selectata"));
            return p;
        }, idClient);
    }
}