package com.example.platformaFilme.model;

import java.time.LocalDate;

public class Comentariu {
    private Long idComentariu;
    private Long idClient;
    private Long idFilm;
    private Long idActor;
    private String textComentariu;
    private LocalDate dataComentariu;
    // pt afisare
    private String numeClient;
    private String titluFilm;
    private String numeActor;

    public Comentariu() {}

    public Long getIdComentariu() { return idComentariu; }
    public void setIdComentariu(Long idComentariu) { this.idComentariu = idComentariu; }
    public Long getIdClient() { return idClient; }
    public void setIdClient(Long idClient) { this.idClient = idClient; }
    public Long getIdFilm() { return idFilm; }
    public void setIdFilm(Long idFilm) { this.idFilm = idFilm; }
    public Long getIdActor() { return idActor; }
    public void setIdActor(Long idActor) { this.idActor = idActor; }
    public String getTextComentariu() { return textComentariu; }
    public void setTextComentariu(String textComentariu) { this.textComentariu = textComentariu; }
    public LocalDate getDataComentariu() { return dataComentariu; }
    public void setDataComentariu(LocalDate dataComentariu) { this.dataComentariu = dataComentariu; }
    public String getNumeClient() { return numeClient; }
    public void setNumeClient(String numeClient) { this.numeClient = numeClient; }
    public String getTitluFilm() { return titluFilm; }
    public void setTitluFilm(String titluFilm) { this.titluFilm = titluFilm; }
    public String getNumeActor() { return numeActor; }
    public void setNumeActor(String numeActor) { this.numeActor = numeActor; }
}