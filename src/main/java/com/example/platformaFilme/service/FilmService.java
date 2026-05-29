package com.example.platformaFilme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.platformaFilme.model.Film;
import com.example.platformaFilme.repository.FilmRepository;

@Service
public class FilmService {
    private final FilmRepository repo;
    public FilmService(FilmRepository repo) { this.repo = repo; }

    public List<Film> findAll() { return repo.findAll(); }
    public Optional<Film> findById(Long id) { return repo.findById(id); }
    public void save(Film f) { repo.insert(f); }
    public void update(Film f) { repo.update(f); }
    public void delete(Long id) { repo.delete(id); }
}