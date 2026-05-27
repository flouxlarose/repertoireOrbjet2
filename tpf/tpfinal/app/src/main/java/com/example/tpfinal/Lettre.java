package com.example.tpfinal;

public class Lettre {
    private char lettre;
    private int valeur;

    private Bonus bonus;

    public Lettre(char lettre) {
        this.lettre = lettre;
        setValeur();
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
        return valeurLettre();
    }

    public void setValeur() {
        switch (Character.toUpperCase(lettre)) {
            case 'E': case 'A': case 'I': case 'O': case 'N':
            case 'S': case 'T': case 'R': case 'U': case 'L':
                this.valeur = 1;
                break;
            case 'D': case 'G': case 'M':
                this.valeur =  2;
                break;
            case 'B': case 'C': case 'P':
                this.valeur =  3;
                break;
            case 'F': case 'H': case 'V':
                this.valeur =  4;
                break;
            case 'J': case 'Q':
                this.valeur =  8;
                break;
            case 'K': case 'W': case 'X': case 'Y': case 'Z':
                this.valeur =  10;
                break;
        }
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }
}
