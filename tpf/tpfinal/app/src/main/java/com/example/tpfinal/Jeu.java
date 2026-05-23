package com.example.tpfinal;

import java.util.AbstractList;
import java.util.ArrayList;

public class Jeu {
    private ArrayList<String> motsTrouver;

    private int scoreTotal;

    private GrilleDeJeu grilleDeJeu;

     public Jeu(){
         grilleDeJeu = new GrilleDeJeu();
         motsTrouver = new ArrayList<>();
         scoreTotal = 0;
     }

     public int nbMotsTrouver() {
         return motsTrouver.size();
     }

    public AbstractList<String> getMotsTrouver() {
        return motsTrouver;
    }

    public GrilleDeJeu getGrilleDeJeu() {
        return grilleDeJeu;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }
}
