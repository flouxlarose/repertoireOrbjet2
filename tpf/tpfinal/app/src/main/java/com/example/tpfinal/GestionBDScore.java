package com.example.tpfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;

public class GestionBDScore extends SQLiteOpenHelper {
    private static GestionBDScore instance;
    private SQLiteDatabase database;

    private Context context;

    public static GestionBDScore getInstance(Context context){
        if(instance == null){
            instance = new GestionBDScore(context);
        }
        return instance;
    }

    private GestionBDScore(@Nullable Context context){
        super(context, "tpf-score", null, 1);
        this.context = context;
        ouvrirConnexionBD();
    }

    public void ouvrirConnexionBD(){
        database = this.getWritableDatabase();
    }

    //public ArrayList<String> bestScore(int limit){
//
    //}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE score (score INT, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
