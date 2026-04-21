package com.example.annexe12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// c'est un singleton car on a besoin d'un seul objet de ce type pour le projet
public class GestionBD extends SQLiteOpenHelper {
    private static GestionBD instance; // reference a lui-meme
    private SQLiteDatabase database;

    public static GestionBD getInstance(Context contexte)
    {
        if (instance == null)
           instance = new GestionBD(contexte);
        return instance;
    }

    // constructeur doit etre privé car singleton
    private GestionBD(@Nullable Context context) {
        super(context, "annexe12", null, 1);
        ouvrirConnexionBD();    // on aurait pu le faire dans le onCreate de l'activité
    }

    // éxécuté une seule fois lorsqu'on installe l'app sur un téléphone
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE inventeur " +
                "(_id  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT, " +
                "origine TEXT, " +
                "invention TEXT, " +
                "annee INTEGER);");
        ajouterInventeur(new Inventeur("Laszlo Biro", "Hongrie", "stylo à bille", 1938), db);
        ajouterInventeur(new Inventeur("Benjamin Franklin", "Etats-Unis", "Paratonnerre", 1752), db);
        ajouterInventeur(new Inventeur("Mary Anderson", "Etats-Unis", "Essuie-glace", 1903), db);
        ajouterInventeur(new Inventeur("Grace Hopper", "Etats-Unis", "Compilateur", 1952), db);
        ajouterInventeur(new Inventeur("Benoit Rouquayrot", "France", "Scaraphandre", 1864), db);
    }

    public void ajouterInventeur(Inventeur i, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put("nom", i.getNom());
        cv.put("origine", i.getOrigine());
        cv.put("invention", i.getInvention());
        cv.put("annee", i.getAnnee());
        db.insert("inventeur", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS inventeur");
        onCreate(db);
    }

    public void ouvrirConnexionBD(){
        database = this.getWritableDatabase();
    }

    public ArrayList<String> retournerInventions(){
        ArrayList<String> listeInvention = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT invention FROM inventeur;", null);
        while (cursor.moveToNext()){
            listeInvention.add(cursor.getString(0));
        }
        cursor.close();
        return listeInvention;
    }

    public boolean aBonneReponse(String nom, String invention){
        String[] params = {nom, invention};
        Cursor c = database.rawQuery("SELECT nom, invention FROM inventeur WHERE nom = ? AND invention = ?", params);
        boolean rep = c.moveToFirst();
        c.close();
        return rep;
    }

    public int trouverIndiceBonneReponse (String nom) throws Exception{
        String[]tab = {nom};
        Cursor c = database.rawQuery("SELECT _id FROM inventeur WHERE nom = ?", tab);
        if(c.moveToFirst()){
            int rep = c.getInt(0) - 1; // les _id commencent à 1
            c.close();
            return rep;
        }
        else
            throw new Exception("Le nom de l'inventeur n'est pas dans la table");
    }
}
