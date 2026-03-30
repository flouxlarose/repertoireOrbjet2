package com.example.tp1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Tracer extends Forme {
    private Path p;

    public Tracer (int couleur, int width){
        super(couleur, width);
        this.p = new Path();
    }

    public void ajouterPoint(Point point){
        if(p.isEmpty()){
            p.moveTo(point.x, point.y);
        }
        else{
            p.lineTo(point.x, point.y);
        }
    }

    public void dessiner(Canvas c){
        Paint crayon;
        crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
        crayon.setColor(getCouleur());
        crayon.setStrokeWidth(getWidth());
        c.drawPath(p, crayon);
    }
}
