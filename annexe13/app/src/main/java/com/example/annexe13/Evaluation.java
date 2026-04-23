package com.example.annexe13;

public class Evaluation {
    private String nom;
    private float evaluation;
    private String microbrasserie;

    public Evaluation(String nom, float evaluation, String microbrasserie){
        this.nom = nom;
        this.evaluation = evaluation;
        this.microbrasserie = microbrasserie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }

    public String getMicrobrasserie() {
        return microbrasserie;
    }

    public void setMicrobrasserie(String microbrasserie) {
        this.microbrasserie = microbrasserie;
    }
}
