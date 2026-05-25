package com.example.tpfinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GestionDBMot extends SQLiteOpenHelper{
    private static GestionDBMot instance;
    private SQLiteDatabase database;

    private Context context;

    public static GestionDBMot getInstance(Context context){
        if(instance == null){
            instance = new GestionDBMot(context);
        }
        return instance;
    }

    private GestionDBMot(@Nullable Context context){
        super(context, "tpf", null, 1);
        this.context = context;
        ouvrirConnexionBD();
    }

    public int executerFichier(SQLiteDatabase db, int ressourceID) throws IOException {
        int compteur = 0;
        InputStream insertStream = context.getResources().openRawResource(ressourceID);
        BufferedReader br = new BufferedReader(new InputStreamReader(insertStream));

        db.execSQL("CREATE TABLE lexique (ortho TEXT,`phon` TEXT,`lemme` TEXT,`cgram` TEXT,`genre` TEXT,`nombre` TEXT,`freqlemfilms` REAL,`freqlemlivres` REAL,`freqfilms` REAL,`freqlivres` REAL,`infover` TEXT,`nbhomogr` INTEGER,`nbhomoph` INTEGER,`islem` INTEGER,`nblettres` INTEGER,`nbphons` INTEGER,`cvcv` TEXT,`p_cvcv` TEXT,`voisorth` INTEGER,`voisphon` INTEGER,`puorth` INTEGER,`puphon` INTEGER,`syll` TEXT,`nbsyll` INTEGER,`cv_cv` TEXT,`orthrenv` TEXT,`phonrenv` TEXT,`orthosyll` TEXT)");

        while (br.ready()){     // while pas vide
            String enonce = br.readLine();
            db.execSQL(enonce);
            compteur ++;
        }
        br.close();
        return compteur;
    }

    public void ouvrirConnexionBD(){
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            executerFichier(db, R.raw.data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean MotExiste(String mot){
        String[] tab = {mot};
        Cursor c = database.rawQuery("SELECT * FROM lexique WHERE ortho = ?", tab);
        boolean rep = c.moveToFirst();
        c.close();
        return rep;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
