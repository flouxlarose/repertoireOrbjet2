package com.example.examen_final;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Random;

public class DrapeauSingleton extends SQLiteOpenHelper{
    private static DrapeauSingleton instance;
    private SQLiteDatabase database;
    private Context context;

    public static DrapeauSingleton getInstance(Context context){
        if(instance == null)
            instance = new DrapeauSingleton(context);
        return instance;
    }

    private DrapeauSingleton(@Nullable Context context) {
        super(context, "exfinal", null, 1);
        this.context = context;
        ouvrirConnexionBD();
    }

    public void ouvrirConnexionBD(){
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table drapeau ( _id INTEGER PRIMARY KEY AUTOINCREMENT, couleurG TEXT, couleurC TEXT, couleurD TEXT, pays TEXT);");
        insererDrapeau(db, new Drapeau("bleu", "blanc", "rouge", "France"));
        insererDrapeau(db, new Drapeau("rouge", "blanc", "rouge", "Pérou"));
        insererDrapeau(db, new Drapeau("bleu", "jaune", "rouge", "Roumanie"));
        insererDrapeau(db, new Drapeau("noir", "jaune", "rouge", "Belgique"));
    }
    public void insererDrapeau (SQLiteDatabase sqLiteDatabase, Drapeau d )
    {
        ContentValues cv = new ContentValues();
        cv.put("couleurG", d.getCouleurG());
        cv.put("couleurC", d.getCouleurC());
        cv.put("couleurD", d.getCouleurD());
        cv.put("pays", d.getPays());
        sqLiteDatabase.insert("drapeau", null, cv );
    }

    public Drapeau randomDrapeau(){
        Random r = new Random();
        String[] id = {String.valueOf(r.nextInt(4) + 1)};
        Cursor c = database.rawQuery("SELECT * FROM drapeau WHERE _id = ?", id);
        if(c.moveToFirst()){
            return new Drapeau(c.getString(1), c.getString(2), c.getString(3), c.getString(4));
        }
        return null;
    }

    public boolean bonDrapeau(String couleurG, String couleurC, String couleurD){
        String[]tab = {couleurG, couleurC, couleurD};
        Cursor c = database.rawQuery("SELECT _id FROM drapeau WHERE couleurG = ? AND couleurC = ? AND couleurD = ?", tab);
        boolean rep = c.moveToFirst();
        c.close();
        return rep;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS drapeau");
        onCreate(db);
    }
}
