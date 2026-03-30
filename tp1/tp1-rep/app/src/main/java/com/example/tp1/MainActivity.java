package com.example.tp1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {
    EcouteurTouch ec;
    EcouteurClick ecClick;
    LinearLayout main;
    LinearLayout outils;
    ChipGroup couleurs;
    SurfaceDessin surf;
    LinearLayout draw;
    Point depart, arrivee;
    String outilActif = "crayon";   // de base l'outil select est crayon...
    Forme formeCourante;
    int couleurCourante = 0xFFFF0000;
    int width = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        draw = findViewById(R.id.draw);
        main = findViewById(R.id.main);
        couleurs = findViewById(R.id.couleurs);
        outils = findViewById(R.id.outils);

        ec = new EcouteurTouch();
        surf = new SurfaceDessin(this);
        surf.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        surf.setOnTouchListener(ec);
        draw.addView(surf);

        for
    }

    private class EcouteurTouch implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent){
            if(v == draw){
                int x = (int)motionEvent.getX();
                int y = (int)motionEvent.getY();

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    depart = new Point(x, y);
                    formeCourante = typeOutil(outilActif);
                    formeCourante.ajouterPoint(depart);
                    surf.invalidate();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    arrivee = new Point(x, y);
                    formeCourante.ajouterPoint(arrivee);
                    surf.invalidate();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    depart = null;
                    arrivee = null;
                }
            }else if(v.getParent() == outils){
                outilActif = v.getTag().toString();
            }

            return true;
        }
    }

    private class EcouteurClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getTag() != null){
                outilActif = v.getTag().toString();
            }
        }
    }

    private Forme typeOutil(String tag){
        switch(tag){
            case "crayon":
                return new Tracer(couleurCourante, width);
        }
        return null;
    }

    private class SurfaceDessin extends View {
        Paint crayon;

        public SurfaceDessin (Context context){
            super(context);
            this.setBackgroundColor(Color.WHITE);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setColor(Color.RED);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas){
            super.onDraw(canvas);
            if (formeCourante != null){
                formeCourante.dessiner(canvas);
            }
        }
    }
}