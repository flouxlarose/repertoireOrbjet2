package com.example.examen_1;

public class Evaluation {
    private int matricule;
    private int nbreService;

    public Evaluation(int matricule, int nbreService){
        this.matricule = matricule;
        this.nbreService = nbreService;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public int getNbreService() {
        return nbreService;
    }

    public void setNbreService(int nbreService) {
        this.nbreService = nbreService;
    }
}
