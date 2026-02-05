package com.example.annex3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    ImageButton bidon;
    ImageButton bouteille;
    ImageButton verre;
    TextView quantiteEau;
    ProgressBar progressBar;
    int objectif = 2000;
    int qu;


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

        ec = new Ecouteur();

        quantiteEau = findViewById(R.id.champProgres);

        bidon = findViewById(R.id.imageBidon);
        bouteille = findViewById(R.id.imageBouteille);
        verre = findViewById(R.id.imageVerre);

        progressBar = findViewById(R.id.progressBar);

        bidon.setOnClickListener(ec);
        bouteille.setOnClickListener(ec);
        verre.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener
    {
        @Override
        public void onClick(View source) {
            int montantEau = Integer.parseInt(quantiteEau.getText().toString());
            if (source == bidon) {
                qu = 1500;
                montantEau += qu;
            } else if (source == bouteille) {
                qu = 550;
                montantEau += qu;
            } else {
                qu = 150;
                montantEau += qu;
            }
            if (montantEau >= objectif) {
                CharSequence text = "OBJECTIF ATTEINT!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(MainActivity.this, text, duration);
                toast.show();
            }

            quantiteEau.setText(String.valueOf(montantEau));
            progressBar.setProgress(montantEau /= 20);
        }
    }

}