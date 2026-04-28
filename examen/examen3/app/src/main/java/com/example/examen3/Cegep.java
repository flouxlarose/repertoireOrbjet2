package com.example.examen3;

public class Cegep {
    private String nomEquipe;
    private int nbreEquipe;
    private String adresse;

    public Cegep(String nomEquipe, int nbreEquipe, String adresse) {
        this.nomEquipe = nomEquipe;
        this.nbreEquipe = nbreEquipe;
        this.adresse = adresse;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public int getNbreEquipe() {
        return nbreEquipe;
    }

    public String getAdresse() {
        return adresse;
    }
}
