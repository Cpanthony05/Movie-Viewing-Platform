package com.example.platformaFilme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.platformaFilme.model.Client;
import com.example.platformaFilme.repository.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository repo;
    public ClientService(ClientRepository repo) { this.repo = repo; }

    public List<Client> findAll() { return repo.findAll(); }
    public Optional<Client> findById(Long id) { return repo.findById(id); }
    public void save(Client c) { repo.insert(c); }
    public void update(Client c) { repo.update(c); }
    public void delete(Long id) { repo.delete(id); }
}