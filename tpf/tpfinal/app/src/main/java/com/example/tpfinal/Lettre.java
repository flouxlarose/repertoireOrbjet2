package com.example.tpfinal;

public class Lettre {
    private char lettre;
    private int valeur;
    private int poids;

    private Bonus bonus;

    public Lettre(char lettre, int valeur, int poids, Bonus bonus) {
        this.lettre = lettre;
        this.valeur = valeur;
        this.bonus = Bonus.NONE;        //par défaut n'a pas de bonus
    }

    public int valeurLettre(){
        if(bonus == Bonus.DOUBLE){
            return valeur * 2;
        }
        else if (bonus == Bonus.TRIPLE) {
            return valeur * 3;
        }

        return valeur;
    }

    public char getLettre() {
        return lettre;
    }

    public void setLettre(char lettre) {
        this.lettre = lettre;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }
}
