package com.example.tp1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;

public class Rectangle extends Forme{
    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;


    public Rectangle (int couleur, int width){
        super(couleur, width);
        this.p1 = null;
        this.p2 = null;
        this.p3 = null;
        this.p4 = null;

    }

    public void ajouterPoint(Point point){
        if(p1 == null){
            p1.x = point.x;
            p1.y = point.y;

        }
        else{
            p2.x = p1.x;
            p2.y = point.y;

            p3.x = point.x;
            p3.y = p1.y;

            p4.x = point.x;
            p4.y = point.y;
        }
    }

    public void dessiner(Canvas c){
        Paint crayon;
        crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
        crayon.setStyle(Paint.Style.STROKE);
        crayon.setColor(getCouleur());
        crayon.setStrokeWidth(getWidth());
        c.drawRect(p1.x, p1.y, p4.x, p4.y, crayon);
    }
}
