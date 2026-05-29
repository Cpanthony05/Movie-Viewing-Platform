package com.example.platformaFilme.model;

public class VersiuneFilm {
    private Long idVersiune;
    private Long idFilm;
    private String format;
    private String rezolutie;
    private String limba;
    private String subtitrare;
    // pt afisare
    private String titluFilm;

    public VersiuneFilm() {}

    public Long getIdVersiune() { return idVersiune; }
    public void setIdVersiune(Long idVersiune) { this.idVersiune = idVersiune; }
    public Long getIdFilm() { return idFilm; }
    public void setIdFilm(Long idFilm) { this.idFilm = idFilm; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    public String getRezolutie() { return rezolutie; }
    public void setRezolutie(String rezolutie) { this.rezolutie = rezolutie; }
    public String getLimba() { return limba; }
    public void setLimba(String limba) { this.limba = limba; }
    public String getSubtitrare() { return subtitrare; }
    public void setSubtitrare(String subtitrare) { this.subtitrare = subtitrare; }
    public String getTitluFilm() { return titluFilm; }
    public void setTitluFilm(String titluFilm) { this.titluFilm = titluFilm; }
}