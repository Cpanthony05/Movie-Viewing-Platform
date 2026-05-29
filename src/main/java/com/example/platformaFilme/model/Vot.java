package com.example.platformaFilme.model;

import java.time.LocalDate;

public class Vot {
    private Long idVot;
    private Long idClient;
    private Long idFilm;
    private Integer punctaj;
    private LocalDate dataVot;
    // pt afisare
    private String numeClient;
    private String titluFilm;

    public Vot() {}

    public Long getIdVot() { return idVot; }
    public void setIdVot(Long idVot) { this.idVot = idVot; }
    public Long getIdClient() { return idClient; }
    public void setIdClient(Long idClient) { this.idClient = idClient; }
    public Long getIdFilm() { return idFilm; }
    public void setIdFilm(Long idFilm) { this.idFilm = idFilm; }
    public Integer getPunctaj() { return punctaj; }
    public void setPunctaj(Integer punctaj) { this.punctaj = punctaj; }
    public LocalDate getDataVot() { return dataVot; }
    public void setDataVot(LocalDate dataVot) { this.dataVot = dataVot; }
    public String getNumeClient() { return numeClient; }
    public void setNumeClient(String numeClient) { this.numeClient = numeClient; }
    public String getTitluFilm() { return titluFilm; }
    public void setTitluFilm(String titluFilm) { this.titluFilm = titluFilm; }
}