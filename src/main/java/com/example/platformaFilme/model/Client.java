package com.example.platformaFilme.model;

public class Client {
    private Long idClient;
    private String nume;
    private String prenume;
    private String telefonAcasa;
    private String adresa;
    private String oras;
    private String email;
    private String telefonMobil;

    public Client() {}

    public Long getIdClient() { return idClient; }
    public void setIdClient(Long idClient) { this.idClient = idClient; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public String getTelefonAcasa() { return telefonAcasa; }
    public void setTelefonAcasa(String telefonAcasa) { this.telefonAcasa = telefonAcasa; }
    public String getAdresa() { return adresa; }
    public void setAdresa(String adresa) { this.adresa = adresa; }
    public String getOras() { return oras; }
    public void setOras(String oras) { this.oras = oras; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefonMobil() { return telefonMobil; }
    public void setTelefonMobil(String telefonMobil) { this.telefonMobil = telefonMobil; }
}