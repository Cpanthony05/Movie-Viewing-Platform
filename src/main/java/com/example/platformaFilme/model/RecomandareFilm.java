package com.example.platformaFilme.model;

import java.math.BigDecimal;

public class RecomandareFilm {
    private Long idFilm;
    private String titlu;
    private BigDecimal rating;
    private String categorie;

    public RecomandareFilm() {}

    public Long getIdFilm() { return idFilm; }
    public void setIdFilm(Long idFilm) { this.idFilm = idFilm; }
    public String getTitlu() { return titlu; }
    public void setTitlu(String titlu) { this.titlu = titlu; }
    public BigDecimal getRating() { return rating; }
    public void setRating(BigDecimal rating) { this.rating = rating; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
}