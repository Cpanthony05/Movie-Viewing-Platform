package com.example.platformaFilme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.platformaFilme.model.Comentariu;
import com.example.platformaFilme.repository.ComentariuRepository;

@Service
public class ComentariuService {
    private final ComentariuRepository repo;
    public ComentariuService(ComentariuRepository repo) { this.repo = repo; }

    public List<Comentariu> findAll() { return repo.findAll(); }
    public Optional<Comentariu> findById(Long id) { return repo.findById(id); }
    public void save(Comentariu c) { repo.insert(c); }
    public void update(Comentariu c) { repo.update(c); }
    public void delete(Long id) { repo.delete(id); }
}