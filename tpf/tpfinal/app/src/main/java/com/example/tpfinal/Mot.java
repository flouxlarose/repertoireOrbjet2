package com.example.tpfinal;

import java.util.ArrayList;

public class Mot {
    private ArrayList<Lettre> motPotentiel;

    public Mot(){
        motPotentiel = new ArrayList<>();
    }

    public int scoreMot () {
        boolean motDouble = false;
        int points = 0;
        for(Lettre lettre : motPotentiel){
            if(lettre.getBonus() == Bonus.MOT_DOUBLE)
                motDouble = true;
            points += lettre.valeurLettre();
        }
        if(motDouble)
            points = points * 2;
        return points;
    }

    public void reset(){
        motPotentiel.clear();
    }

    public void ajouterLettre(Lettre lettre){
        motPotentiel.add(lettre);
    }

    public ArrayList<Lettre> getMotPotentiel() {
        return motPotentiel;
    }

    public String getMot(){
        StringBuilder mot = new StringBuilder();
        for (Lettre lettre : motPotentiel){
            mot.append(lettre.getLettre());
        }
        return mot.toString();
    }

    public int longueur(){
        return motPotentiel.size();
    }
}
