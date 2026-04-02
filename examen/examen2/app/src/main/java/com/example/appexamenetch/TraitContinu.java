package com.example.appexamenetch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class TraitContinu {
    private Path p;
    private int width;

    public TraitContinu(int width ){
        p = new Path();
        this.width = width;
    }

    public Path getP() {
        return p;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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
        crayon.setStyle(Paint.Style.STROKE);
        crayon.setStrokeWidth(width);
        crayon.setColor(Color.BLACK);
        c.drawPath(p, crayon);
    }
}
