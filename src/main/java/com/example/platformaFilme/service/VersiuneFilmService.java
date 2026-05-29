package com.example.platformaFilme.service;

import com.example.platformaFilme.model.VersiuneFilm;
import com.example.platformaFilme.repository.VersiuneFilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VersiuneFilmService {
    private final VersiuneFilmRepository repo;
    public VersiuneFilmService(VersiuneFilmRepository repo) { this.repo = repo; }

    public List<VersiuneFilm> findAll() { return repo.findAll(); }
    public Optional<VersiuneFilm> findById(Long id) { return repo.findById(id); }
    public List<VersiuneFilm> findByFilmId(Long idFilm) { return repo.findByFilmId(idFilm); }
    public void save(VersiuneFilm v) { repo.insert(v); }
    public void update(VersiuneFilm v) { repo.update(v); }
    public void delete(Long id) { repo.delete(id); }
}