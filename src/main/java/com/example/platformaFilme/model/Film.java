package com.example.platformaFilme.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Film {
    private Long idFilm;
    private String titlu;
    private String descriere;
    private String categorie;
    private LocalDate dataLansare;
    private BigDecimal rating;

    public Film() {}

    public Long getIdFilm() { return idFilm; }
    public void setIdFilm(Long idFilm) { this.idFilm = idFilm; }
    public String getTitlu() { return titlu; }
    public void setTitlu(String titlu) { this.titlu = titlu; }
    public String getDescriere() { return descriere; }
    public void setDescriere(String descriere) { this.descriere = descriere; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public LocalDate getDataLansare() { return dataLansare; }
    public void setDataLansare(LocalDate dataLansare) { this.dataLansare = dataLansare; }
    public BigDecimal getRating() { return rating; }
    public void setRating(BigDecimal rating) { this.rating = rating; }
}