package com.example.platformaFilme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.platformaFilme.model.Actor;
import com.example.platformaFilme.repository.ActorRepository;

@Service
public class ActorService {
    private final ActorRepository repo;
    public ActorService(ActorRepository repo) { this.repo = repo; }

    public List<Actor> findAll() { return repo.findAll(); }
    public Optional<Actor> findById(Long id) { return repo.findById(id); }
    public void save(Actor a) { repo.insert(a); }
    public void update(Actor a) { repo.update(a); }
    public void delete(Long id) { repo.delete(id); }
}