package com.example.annexe2;

public class Compte {
    private String nom;
    private int solde;

    public Compte(String nom, int solde){
        this.nom = nom;
        this.solde = solde;
    }

    public String getNom() {
        return nom;
    }

    public int getSolde() {
        return solde;
    }

    // diminuer le solde suite à un transfert
    public boolean transfert(int montant){
        if(solde >= montant){
            solde -= montant;
            return true;
        }
        return false;
    }
}
