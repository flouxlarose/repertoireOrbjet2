package com.example.examen_final;

public class Drapeau {
    private String couleurG;
    private String couleurC;
    private String couleurD;
    private String pays;

    public Drapeau(String couleurG, String couleurC, String couleurD, String pays){
        this.couleurG = couleurG;
        this.couleurC = couleurC;
        this.couleurD = couleurD;
        this.pays = pays;
    }

    public String getCouleurG() {
        return couleurG;
    }

    public void setCouleurG(String couleurG) {
        this.couleurG = couleurG;
    }

    public String getCouleurC() {
        return couleurC;
    }

    public void setCouleurC(String couleurC) {
        this.couleurC = couleurC;
    }

    public String getCouleurD() {
        return couleurD;
    }

    public void setCouleurD(String couleurD) {
        this.couleurD = couleurD;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}
