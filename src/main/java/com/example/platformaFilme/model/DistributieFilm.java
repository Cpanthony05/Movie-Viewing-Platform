package com.example.platformaFilme.model;

public class DistributieFilm {
    private Long idFilm;
    private Long idActor;
    private String rol;
    // pt afisare
    private String titluFilm;
    private String numeActor;

    public DistributieFilm() {}

    public Long getIdFilm() { return idFilm; }
    public void setIdFilm(Long idFilm) { this.idFilm = idFilm; }
    public Long getIdActor() { return idActor; }
    public void setIdActor(Long idActor) { this.idActor = idActor; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public String getTitluFilm() { return titluFilm; }
    public void setTitluFilm(String titluFilm) { this.titluFilm = titluFilm; }
    public String getNumeActor() { return numeActor; }
    public void setNumeActor(String numeActor) { this.numeActor = numeActor; }
}