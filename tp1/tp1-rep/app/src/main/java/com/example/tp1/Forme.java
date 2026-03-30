package com.example.tp1;

import android.graphics.Canvas;
import android.graphics.Point;

public abstract class Forme {
    private int couleur;
    private int width;

    public Forme(int couleur, int width){
        this.couleur = couleur;
        this.width = width;
    }

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public abstract void dessiner (Canvas c);

    public abstract void ajouterPoint(Point point);
}
