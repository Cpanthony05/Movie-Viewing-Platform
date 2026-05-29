package com.example.platformaFilme.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.platformaFilme.model.DistributieFilm;
import com.example.platformaFilme.repository.DistributieFilmRepository;

@Service
public class DistributieFilmService {
    private final DistributieFilmRepository repo;
    public DistributieFilmService(DistributieFilmRepository repo) { this.repo = repo; }

    public List<DistributieFilm> findAll() { return repo.findAll(); }
    public List<DistributieFilm> findByFilm(Long idFilm) { return repo.findByFilm(idFilm); }
    public void save(DistributieFilm d) { repo.insert(d); }
    public void update(Long idFilm, Long idActor, String rol) { repo.update(idFilm, idActor, rol); }
    public void delete(Long idFilm, Long idActor) { repo.delete(idFilm, idActor); }
}