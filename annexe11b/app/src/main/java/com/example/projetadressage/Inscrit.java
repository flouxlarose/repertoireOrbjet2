package com.example.projetadressage;


import bla.HashtableAssociation;

public class Inscrit {
    private String nom;
    private String prenom;
    private String adresse;
    private String capitale;
    private String etat;
    private String codeZip;

    public Inscrit(String nom, String prenom, String adresse, String capitale, String etat, String codeZip) throws AdresseException{
        // vérifier si la capitale fait partie de l'état à l'aide d'une Hashtable secrète ( classe HashtableAssociation )
        HashtableAssociation h = new HashtableAssociation();
        String etatTrouver = h.get(capitale);
        if(! etatTrouver.equals(etat)){
            throw new AdresseException(capitale, etat);     // on fait pas de catch encore pour pouvoir attraper l'exeption dans le main activity
        }

        if(nom.trim().isEmpty()){
            throw new AdresseException("nom");
        }
        if(prenom.isEmpty()){
            throw new AdresseException("prenom");
        }
        if(adresse.isEmpty()){
            throw new AdresseException("adresse");
        }
        if(codeZip.isEmpty()){
            throw new AdresseException("code Zip");
        }

        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.etat = etat;
        this.codeZip = codeZip;
        this.capitale = capitale;






    }
}
