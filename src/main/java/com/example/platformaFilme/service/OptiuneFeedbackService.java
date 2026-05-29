package com.example.platformaFilme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.platformaFilme.model.OptiuneFeedback;
import com.example.platformaFilme.repository.OptiuneFeedbackRepository;

@Service
public class OptiuneFeedbackService {
    private final OptiuneFeedbackRepository repo;
    public OptiuneFeedbackService(OptiuneFeedbackRepository repo) { this.repo = repo; }

    public List<OptiuneFeedback> findAll() { return repo.findAll(); }
    public Optional<OptiuneFeedback> findById(Long id) { return repo.findById(id); }
    public void save(OptiuneFeedback o) { repo.insert(o); }
    public void update(OptiuneFeedback o) { repo.update(o); }
    public void delete(Long id) { repo.delete(id); }
}