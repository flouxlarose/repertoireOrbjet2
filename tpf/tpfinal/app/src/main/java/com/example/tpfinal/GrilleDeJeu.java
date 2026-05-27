package com.example.tpfinal;

import java.util.ArrayList;
import java.util.Random;

public class GrilleDeJeu {
    private Lettre[][] grilleJeu;
    private ArrayList<Character> listeLettre = new ArrayList<>();

    public GrilleDeJeu(){
        genererListeLettre();
        grilleJeu = new Lettre[4][4];       // grille de 4 x 4
        creerGrille();
        setBonus();
    }

    private void genererListeLettre(){
        // 12 E
        listeLettre.add('E');
        listeLettre.add('E');
        listeLettre.add('E');
        listeLettre.add('E');
        listeLettre.add('E');
        listeLettre.add('E');
        listeLettre.add('E');
        listeLettre.add('E');
        listeLettre.add('E');
        listeLettre.add('E');
        listeLettre.add('E');
        listeLettre.add('E');

        // 9 A
        listeLettre.add('A');
        listeLettre.add('A');
        listeLettre.add('A');
        listeLettre.add('A');
        listeLettre.add('A');
        listeLettre.add('A');
        listeLettre.add('A');
        listeLettre.add('A');
        listeLettre.add('A');

        // 8 I
        listeLettre.add('I');
        listeLettre.add('I');
        listeLettre.add('I');
        listeLettre.add('I');
        listeLettre.add('I');
        listeLettre.add('I');
        listeLettre.add('I');
        listeLettre.add('I');

        // 8 S
        listeLettre.add('S');
        listeLettre.add('S');
        listeLettre.add('S');
        listeLettre.add('S');
        listeLettre.add('S');
        listeLettre.add('S');
        listeLettre.add('S');
        listeLettre.add('S');

        // 8 N
        listeLettre.add('N');
        listeLettre.add('N');
        listeLettre.add('N');
        listeLettre.add('N');
        listeLettre.add('N');
        listeLettre.add('N');
        listeLettre.add('N');
        listeLettre.add('N');

        // 8 R
        listeLettre.add('R');
        listeLettre.add('R');
        listeLettre.add('R');
        listeLettre.add('R');
        listeLettre.add('R');
        listeLettre.add('R');
        listeLettre.add('R');
        listeLettre.add('R');

        // 7 T
        listeLettre.add('T');
        listeLettre.add('T');
        listeLettre.add('T');
        listeLettre.add('T');
        listeLettre.add('T');
        listeLettre.add('T');
        listeLettre.add('T');

        // 7 O
        listeLettre.add('O');
        listeLettre.add('O');
        listeLettre.add('O');
        listeLettre.add('O');
        listeLettre.add('O');
        listeLettre.add('O');
        listeLettre.add('O');

        // 6 U
        listeLettre.add('U');
        listeLettre.add('U');
        listeLettre.add('U');
        listeLettre.add('U');
        listeLettre.add('U');
        listeLettre.add('U');

        // 6 L
        listeLettre.add('L');
        listeLettre.add('L');
        listeLettre.add('L');
        listeLettre.add('L');
        listeLettre.add('L');
        listeLettre.add('L');

        // 5 D
        listeLettre.add('D');
        listeLettre.add('D');
        listeLettre.add('D');
        listeLettre.add('D');
        listeLettre.add('D');

        // 5 C
        listeLettre.add('C');
        listeLettre.add('C');
        listeLettre.add('C');
        listeLettre.add('C');
        listeLettre.add('C');

        // 4 M
        listeLettre.add('M');
        listeLettre.add('M');
        listeLettre.add('M');
        listeLettre.add('M');

        // 4 P
        listeLettre.add('P');
        listeLettre.add('P');
        listeLettre.add('P');
        listeLettre.add('P');

        // 4 V
        listeLettre.add('V');
        listeLettre.add('V');
        listeLettre.add('V');
        listeLettre.add('V');

        // 4 G
        listeLettre.add('G');
        listeLettre.add('G');
        listeLettre.add('G');
        listeLettre.add('G');

        // 3 B
        listeLettre.add('B');
        listeLettre.add('B');
        listeLettre.add('B');

        // 3 F
        listeLettre.add('F');
        listeLettre.add('F');
        listeLettre.add('F');

        // 2 H
        listeLettre.add('H');
        listeLettre.add('H');

        // 2 J
        listeLettre.add('J');
        listeLettre.add('J');

        // 2 Q
        listeLettre.add('Q');
        listeLettre.add('Q');

        // 2 Y
        listeLettre.add('Y');
        listeLettre.add('Y');

        // 1 K
        listeLettre.add('K');

        // 1 W
        listeLettre.add('W');

        // 1 X
        listeLettre.add('X');

        // 1 Z
        listeLettre.add('Z');
    }

    public void creerGrille(){
        for (int i = 0; i < grilleJeu.length; i++) {
            for (int j = 0; j < grilleJeu[i].length; j++) {
                grilleJeu[i][j] = new Lettre(randomLettre());           // crée l'objet
            }
        }
    }

    public void setBonus(){
        Random r = new Random();
        int ligne = 0;
        int colonne = 0;

        int compteurDouble = 0;
        int compteurTriple = 0;
        int compteurMotDouble = 0;

        while (compteurDouble < 2){
            ligne = r.nextInt(4);
            colonne = r.nextInt(4);
            if(grilleJeu[ligne][colonne].getBonus() == Bonus.NONE){
                grilleJeu[ligne][colonne].setBonus(Bonus.DOUBLE);
                compteurDouble ++;
            }
        }

        while (compteurTriple < 1){
            ligne = r.nextInt(4);
            colonne = r.nextInt(4);
            if(grilleJeu[ligne][colonne].getBonus() == Bonus.NONE) {
                grilleJeu[ligne][colonne].setBonus(Bonus.TRIPLE);
                compteurTriple++;
            }
        }

        while (compteurMotDouble < 1){
            ligne = r.nextInt(4);
            colonne = r.nextInt(4);
            if(grilleJeu[ligne][colonne].getBonus() == Bonus.NONE) {
                grilleJeu[ligne][colonne].setBonus(Bonus.MOT_DOUBLE);
                compteurMotDouble++;
            }
        }

    }

    public char randomLettre(){
        Random r = new Random();
        int nombreRandom = r.nextInt(listeLettre.size());
        return listeLettre.get(nombreRandom);
    }

    public boolean estAdjacent(int ligne1, int colonne1, int ligne2, int colonne2){
        int deltaLigne = Math.abs(ligne1 - ligne2);
        int deltaColonnne = Math.abs(colonne1 - colonne2);

        return (deltaLigne <= 1 && deltaColonnne <= 1 && !(deltaLigne == 0 && deltaColonnne == 0));
    }
}
