package com.example.platformaFilme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.platformaFilme.model.Vot;
import com.example.platformaFilme.repository.VotRepository;

@Service
public class VotService {
    private final VotRepository repo;
    public VotService(VotRepository repo) { this.repo = repo; }

    public List<Vot> findAll() { return repo.findAll(); }
    public Optional<Vot> findById(Long id) { return repo.findById(id); }
    public void save(Vot v) { repo.insert(v); }
    public void update(Vot v) { repo.update(v); }
    public void delete(Long id) { repo.delete(id); }
}