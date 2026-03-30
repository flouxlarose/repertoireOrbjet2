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

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Forme> formesList = new ArrayList<>();
    EcouteurTouch ecTouch;
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

        ecTouch = new EcouteurTouch();
        ecClick = new EcouteurClick();
        surf = new SurfaceDessin(this);
        surf.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        surf.setOnTouchListener(ecTouch);
        draw.addView(surf);

        for (int i =0; i < outils.getChildCount(); i++){
            outils.getChildAt(i).setOnClickListener(ecClick);
        }

        for(int i = 0; i < couleurs.getChildCount(); i++){
            if(couleurs.getChildAt(i) instanceof Chip){
                couleurs.getChildAt(i).setOnClickListener(ecClick);
            }
        }
    }

    private class EcouteurTouch implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent){
            int x = (int)motionEvent.getX();
            int y = (int)motionEvent.getY();

            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                depart = new Point(x, y);
                formeCourante = typeOutil(outilActif);
                formeCourante.ajouterPoint(depart);
                formesList.add(formeCourante);
                surf.invalidate();
            } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                arrivee = new Point(x, y);
                formeCourante.ajouterPoint(arrivee);
                surf.invalidate();
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                depart = null;
                arrivee = null;
            }

            return true;
        }
    }

    private class EcouteurClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (v.getParent() == couleurs){
                couleurCourante = Color.parseColor(v.getTag().toString());
            }
            if(v.getParent() == outils){
                if(v.getTag() != null){
                    outilActif = v.getTag().toString();
                }
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
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas){
            super.onDraw(canvas);
            if(formeCourante != null){
                formeCourante.dessiner(canvas);
            }
            for (Forme f: formesList) {
                f.dessiner(canvas);
            }
        }
    }
}