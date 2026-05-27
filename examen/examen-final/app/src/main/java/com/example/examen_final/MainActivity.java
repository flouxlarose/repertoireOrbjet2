package com.example.examen_final;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView question;
    LinearLayout couleurG;
    LinearLayout couleurC;
    LinearLayout couleurD;
    Button confirmer;
    LinearLayout conteneurCouleurs;
    DrapeauSingleton instance;
    Ecouteur1 e1;
    Ecouteur2 e2;

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

        question = findViewById(R.id.texteQuestion);
        couleurG = findViewById(R.id.conteneurG);
        couleurC = findViewById(R.id.conteneurC);
        couleurD = findViewById(R.id.conteneurD);
        conteneurCouleurs = findViewById(R.id.conteneurCouleurs);

        confirmer = findViewById(R.id.boutonConfirmer);

        instance = DrapeauSingleton.getInstance(getApplicationContext());
        e1 = new Ecouteur1();
        e2 = new Ecouteur2();

        for (int i = 0; i < conteneurCouleurs.getChildCount(); i++){
            TextView couleur = (TextView) conteneurCouleurs.getChildAt(i);
            couleur.setOnDragListener(e1);
            couleur.setOnTouchListener(e1);
        }

        confirmer.setOnClickListener(e2);

        Drapeau drapeauReponse = instance.randomDrapeau();
        question.setText("Dessinez le drapeau de la: " + drapeauReponse.getPays());
    }

    public class Ecouteur2 implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(instance.bonDrapeau(couleurG.getBackground().toString(), couleurC.getBackground().toString(), couleurD.getBackground().toString())){
                question.setText("BRAVO BONNE RÉPONSE");
            }
            else {
                question.setText("DSL");
            }
        }
    }

    public class Ecouteur1 implements View.OnTouchListener, View.OnDragListener{
        @Override
        public boolean onTouch(View v, MotionEvent e){
            if(e.getAction() == MotionEvent.ACTION_DOWN){
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(null, shadowBuilder, v, 0);
            }
            return true;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    break;
                case DragEvent.ACTION_DROP:

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    TextView couleur = (TextView) event.getLocalState();
                    LinearLayout destination = (LinearLayout) v; // chercher la colonne d'arrivé
                    destination.addView(couleur);// ajouter dans la colonne de destiontion
                    couleur.setVisibility(VISIBLE); // il était encore invisible .
                    destination.setBackgroundColor(Color.BLUE);
                    break;

            }
            return true;
        }
    }
}