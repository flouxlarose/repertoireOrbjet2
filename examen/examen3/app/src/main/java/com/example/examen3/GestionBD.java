package com.example.examen3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GestionBD extends SQLiteOpenHelper {
    private static GestionBD instance;
    private SQLiteDatabase database;

    public static GestionBD getInstance(Context context){
        if(instance == null)
            instance = new GestionBD(context);
        return instance;
    }

    private GestionBD(@Nullable Context context){
        super(context, "examen3", null, 1);
        ouvrirConnexionBD();
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE cegep" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT," +
                "nbreEquipe INTEGER," +
                "adresse TEXT);");
    }

    public void insert(){
        ajouterCegep(new Cegep("Cavaliers de Bois-de-Boulogne", 5, "10555 avenue de Bois-de-Boulogne Montréal, QC"));
        ajouterCegep(new Cegep("Cheminots de St-Jérôme", 12, "455 Rue Fournier, Saint-Jérôme, QC"));
        ajouterCegep(new Cegep("Diablos de Trois-Rivières", 14, "3500 Rue de Courval, Trois-Rivières, QC"));
        ajouterCegep(new Cegep("Spartiates du Vieux Montréal", 9, "255 rue Ontario Est, Montréal, QC"));
    }

    public ArrayList<String> nomEquipes(){
        ArrayList<String> listeEquipes = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT nom FROM cegep;", null);
        while(c.moveToNext()){
            listeEquipes.add(c.getString(0));
        }
        c.close();
        return listeEquipes;
    }

    public Cegep cegepSelonNom(String nom){
        String[]tab = {nom};
        Cursor c = database.rawQuery("SELECT * FROM cegep WHERE nom = ?", tab);
        if(c.moveToNext()){
            Cegep cegep = new Cegep(c.getString(1), c.getInt(2), c.getString(3));
            // le 0 est le _ID
            return cegep;
        }
        return null;
    }

    public void ajouterCegep(Cegep c){
        ContentValues cv = new ContentValues();
        cv.put("nom", c.getNomEquipe());
        cv.put("nbreEquipe", c.getNbreEquipe());
        cv.put("adresse", c.getAdresse());
        database.insert("cegep", null, cv);
    }

    public void ouvrirConnexionBD(){
        database = this.getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ratingBiere");
        onCreate(db);
    }
}
