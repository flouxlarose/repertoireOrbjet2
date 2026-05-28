package com.example.tpfinal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GestionDBMot instanceMot;
    private GestionBDScore instanceScore;
    private GrilleDeJeu grilleDeJeu;
    private ArrayList<String> motsTrouves;
    private TextView pointsMot, pointsTotal, motFormer;
    private SeekBar timerbar;
    private CountDownTimer timer;
    private int temps = 50000;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        instanceMot = GestionDBMot.getInstance(getApplicationContext());
        instanceScore = GestionBDScore.getInstance(getApplicationContext());

        pointsMot = findViewById(R.id.pointsMot);
        pointsTotal = findViewById(R.id.pointsTotal);
        motFormer = findViewById(R.id.motFormer);

        motsTrouves = new ArrayList<>();
        grilleDeJeu = new GrilleDeJeu();

        timerbar = findViewById(R.id.timer);
        timerbar.setMax(temps);
        timerbar.setProgress(temps);
        timerbar.setEnabled(false);

        timer = new CountDownTimer(temps, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerbar.setProgress((int) millisUntilFinished);
            }

            @Override
            public void onFinish() {
                // TEMPS ÉCOULÉ
            }
        }.start();



    }
}