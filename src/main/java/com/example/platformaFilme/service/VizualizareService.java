package com.example.platformaFilme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.platformaFilme.model.Vizualizare;
import com.example.platformaFilme.repository.VizualizareRepository;

@Service
public class VizualizareService {
    private final VizualizareRepository repo;
    public VizualizareService(VizualizareRepository repo) { this.repo = repo; }

    public List<Vizualizare> findAll() { return repo.findAll(); }
    public Optional<Vizualizare> findById(Long id) { return repo.findById(id); }
    public void save(Vizualizare v) { repo.insert(v); }
    public void update(Vizualizare v) { repo.update(v); }
    public void delete(Long id) { repo.delete(id); }
}