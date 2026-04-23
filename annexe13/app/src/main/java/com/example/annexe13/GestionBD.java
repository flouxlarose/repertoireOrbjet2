package com.example.annexe13;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.Settings;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
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
        super(context, "annexe13", null, 1);
        ouvrirConnexionBD();
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE ratingBiere" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT," +
                "microbrasserie TEXT," +
                "evaluation REAL);");
    }

    public void ajouterBiere(Evaluation e){
        ContentValues cv = new ContentValues();
        cv.put("nom", e.getNom());
        cv.put("microbrasserie", e.getMicrobrasserie());
        cv.put("evaluation", e.getEvaluation());
        database.insert("ratingBiere", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ratingBiere");
        onCreate(db);
    }

    public void ouvrirConnexionBD(){
        database = this.getWritableDatabase();
    }

    public ArrayList<String> meilleurRating() throws Exception{
        Cursor cursor = database.rawQuery("SELECT nom FROM ratingBiere ORDER BY evaluation DESC LIMIT 3", null);
        ArrayList<String> v = new ArrayList();

        while(cursor.moveToNext()) {
            v.add(cursor.getString(0));
        }
        return v;
    }

}
