package com.example.platformaFilme.model;

import java.time.LocalDate;

public class ProfilClient {
    private Long idClient;
    private String numeComplet;
    private String filmVizionat;
    private String categorie;
    private LocalDate dataVizualizare;
    private String stare;
    private Integer votAcordat;
    private String textComentariu;
    private String sentimentComentariu;
    private String caracterizareSelectata;

    public ProfilClient() {}

    public Long getIdClient() { return idClient; }
    public void setIdClient(Long idClient) { this.idClient = idClient; }
    public String getNumeComplet() { return numeComplet; }
    public void setNumeComplet(String numeComplet) { this.numeComplet = numeComplet; }
    public String getFilmVizionat() { return filmVizionat; }
    public void setFilmVizionat(String filmVizionat) { this.filmVizionat = filmVizionat; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public LocalDate getDataVizualizare() { return dataVizualizare; }
    public void setDataVizualizare(LocalDate dataVizualizare) { this.dataVizualizare = dataVizualizare; }
    public String getStare() { return stare; }
    public void setStare(String stare) { this.stare = stare; }
    public Integer getVotAcordat() { return votAcordat; }
    public void setVotAcordat(Integer votAcordat) { this.votAcordat = votAcordat; }
    public String getTextComentariu() { return textComentariu; }
    public void setTextComentariu(String textComentariu) { this.textComentariu = textComentariu; }
    public String getSentimentComentariu() { return sentimentComentariu; }
    public void setSentimentComentariu(String sentimentComentariu) { this.sentimentComentariu = sentimentComentariu; }
    public String getCaracterizareSelectata() { return caracterizareSelectata; }
    public void setCaracterizareSelectata(String caracterizareSelectata) { this.caracterizareSelectata = caracterizareSelectata; }
}