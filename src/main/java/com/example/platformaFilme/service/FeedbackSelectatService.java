package com.example.platformaFilme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.platformaFilme.model.FeedbackSelectat;
import com.example.platformaFilme.repository.FeedbackSelectatRepository;

@Service
public class FeedbackSelectatService {
    private final FeedbackSelectatRepository repo;
    public FeedbackSelectatService(FeedbackSelectatRepository repo) { this.repo = repo; }

    public List<FeedbackSelectat> findAll() { return repo.findAll(); }
    public Optional<FeedbackSelectat> findById(Long id) { return repo.findById(id); }
    public void save(FeedbackSelectat fs) { repo.insert(fs); }
    public void update(FeedbackSelectat fs) { repo.update(fs); }
    public void delete(Long id) { repo.delete(id); }
}