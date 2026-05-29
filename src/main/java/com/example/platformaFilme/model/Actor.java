package com.example.platformaFilme.model;

import java.time.LocalDate;

public class Actor {
    private Long idActor;
    private String numeScena;
    private String prenume;
    private String numeFamilie;
    private LocalDate dataNasterii;

    public Actor() {}

    public Long getIdActor() { return idActor; }
    public void setIdActor(Long idActor) { this.idActor = idActor; }
    public String getNumeScena() { return numeScena; }
    public void setNumeScena(String numeScena) { this.numeScena = numeScena; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public String getNumeFamilie() { return numeFamilie; }
    public void setNumeFamilie(String numeFamilie) { this.numeFamilie = numeFamilie; }
    public LocalDate getDataNasterii() { return dataNasterii; }
    public void setDataNasterii(LocalDate dataNasterii) { this.dataNasterii = dataNasterii; }
}