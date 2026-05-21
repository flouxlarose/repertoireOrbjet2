package com.example.tpfinal;

public class GrilleDeJeu {
    private Lettre[][] grilleJeu;

    public GrilleDeJeu(){
        grilleJeu = new Lettre[4][4];
    }

    public void creerGrille(){

    }

    public boolean estAdjacent(int ligne1, int colonne1, int ligne2, int colonne2){
        int deltaLigne = Math.abs(ligne1 - ligne2);
        int deltaColonnne = Math.abs(colonne1 - colonne2);

        return (deltaLigne <= 1 && deltaColonnne <= 1 && !(deltaLigne == 0 && deltaColonnne == 0))
    }
}
