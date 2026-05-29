package com.example.platformaFilme.model;

import java.time.LocalDate;

public class FeedbackSelectat {
    private Long idFeedback;
    private Long idClient;
    private Long idFilm;
    private Long idOptiune;
    private LocalDate dataFeedback;
    // pt afisare
    private String numeClient;
    private String titluFilm;
    private String descriereOptiune;

    public FeedbackSelectat() {}

    public Long getIdFeedback() { return idFeedback; }
    public void setIdFeedback(Long idFeedback) { this.idFeedback = idFeedback; }
    public Long getIdClient() { return idClient; }
    public void setIdClient(Long idClient) { this.idClient = idClient; }
    public Long getIdFilm() { return idFilm; }
    public void setIdFilm(Long idFilm) { this.idFilm = idFilm; }
    public Long getIdOptiune() { return idOptiune; }
    public void setIdOptiune(Long idOptiune) { this.idOptiune = idOptiune; }
    public LocalDate getDataFeedback() { return dataFeedback; }
    public void setDataFeedback(LocalDate dataFeedback) { this.dataFeedback = dataFeedback; }
    public String getNumeClient() { return numeClient; }
    public void setNumeClient(String numeClient) { this.numeClient = numeClient; }
    public String getTitluFilm() { return titluFilm; }
    public void setTitluFilm(String titluFilm) { this.titluFilm = titluFilm; }
    public String getDescriereOptiune() { return descriereOptiune; }
    public void setDescriereOptiune(String descriereOptiune) { this.descriereOptiune = descriereOptiune; }
}