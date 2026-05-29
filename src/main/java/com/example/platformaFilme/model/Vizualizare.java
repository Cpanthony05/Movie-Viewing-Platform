package com.example.platformaFilme.model;

import java.time.LocalDate;

public class Vizualizare {
    private Long idVizualizare;
    private Long idClient;
    private Long idVersiune;
    private LocalDate dataVizualizare;
    private Integer durataMin;
    private String stare;
    // pt afisare
    private String numeClient;
    private String titluFilm;

    public Vizualizare() {}

    public Long getIdVizualizare() { return idVizualizare; }
    public void setIdVizualizare(Long idVizualizare) { this.idVizualizare = idVizualizare; }
    public Long getIdClient() { return idClient; }
    public void setIdClient(Long idClient) { this.idClient = idClient; }
    public Long getIdVersiune() { return idVersiune; }
    public void setIdVersiune(Long idVersiune) { this.idVersiune = idVersiune; }
    public LocalDate getDataVizualizare() { return dataVizualizare; }
    public void setDataVizualizare(LocalDate dataVizualizare) { this.dataVizualizare = dataVizualizare; }
    public Integer getDurataMin() { return durataMin; }
    public void setDurataMin(Integer durataMin) { this.durataMin = durataMin; }
    public String getStare() { return stare; }
    public void setStare(String stare) { this.stare = stare; }
    public String getNumeClient() { return numeClient; }
    public void setNumeClient(String numeClient) { this.numeClient = numeClient; }
    public String getTitluFilm() { return titluFilm; }
    public void setTitluFilm(String titluFilm) { this.titluFilm = titluFilm; }
}   