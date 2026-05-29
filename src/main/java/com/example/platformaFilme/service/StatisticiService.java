package com.example.platformaFilme.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.platformaFilme.model.ProfilClient;
import com.example.platformaFilme.model.RecomandareFilm;
import com.example.platformaFilme.repository.StatisticiRepository;

@Service
public class StatisticiService {
    private final StatisticiRepository repo;
    public StatisticiService(StatisticiRepository repo) { this.repo = repo; }

    public List<RecomandareFilm> genereazaRecomandari(Long idClient, int numar) {
        return repo.genereazaRecomandari(idClient, numar);
    }
    public List<String[]> actoriFrecventi(Long idClient) {
        return repo.actoriFrecventi(idClient);
    }
    public List<ProfilClient> getProfilClientView(Long idClient) {
        return repo.getProfilClientView(idClient);
    }
}