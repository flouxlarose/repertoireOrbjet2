package com.example.appexamenetch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout sectionHaut;
    SurfaceDessin surf;
    LinearLayout sectionBas;
    Ecouteur ec;
    SeekBar widthBar;
    TraitContinu forme;
    Point p;
    EcouteurSeekBar ecSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sectionHaut = findViewById(R.id.sectionHaut);
        sectionBas = findViewById(R.id.sectionBas);
        widthBar = findViewById(R.id.seekBar);

        sectionHaut.setBackgroundColor(getColor((R.color.sable)));
        widthBar.setProgress(4);

        ec = new Ecouteur();
        ecSeekBar = new EcouteurSeekBar();

        surf = new SurfaceDessin(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));
        sectionHaut.addView(surf);

        forme = new TraitContinu(widthBar.getProgress());
        p = new Point(525,700);
        System.out.println(p);
        forme.ajouterPoint(p);

        widthBar.setOnSeekBarChangeListener(ecSeekBar);

        for(int i = 0; i < sectionBas.getChildCount(); i++){
            if (sectionBas.getChildAt(i) instanceof LinearLayout){
                for (int j = 0; j < ((LinearLayout)sectionBas.getChildAt(i)).getChildCount(); j++) {
                    if (((LinearLayout) sectionBas.getChildAt(i)).getChildAt(j) instanceof ImageButton){
                        ((LinearLayout) sectionBas.getChildAt(i)).getChildAt(j).setOnClickListener(ec);
                    }
                }
            }

        }
    }

    private class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View v){
            if (v.getParent().getParent() == sectionBas){
                if(v.getTag().toString().equals("haut")){
                    p.y = (p.y - 20);
                    forme.ajouterPoint(p);
                    surf.invalidate();
                } else if (v.getTag().toString().equals("droite")) {
                    p.x = (p.x + 20);
                    forme.ajouterPoint(p);
                    surf.invalidate();
                } else if (v.getTag().toString().equals("bas")) {
                    p.y = (p.y + 20);
                    forme.ajouterPoint(p);
                    surf.invalidate();
                } else if (v.getTag().toString().equals("gauche")) {
                    p.x = (p.x - 20);
                    forme.ajouterPoint(p);
                    surf.invalidate();
                }
            }
        }
    }

    private class EcouteurSeekBar implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(forme != null){
                forme.setWidth(progress);
                surf.invalidate();
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private class SurfaceDessin extends View {
        public SurfaceDessin(Context context){
            super(context);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas){
            super.onDraw(canvas);
            if (forme != null){

                forme.dessiner(canvas);
            }
        }
    }
}