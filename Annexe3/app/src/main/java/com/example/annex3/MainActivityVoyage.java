package com.example.annex3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.Format;

public class MainActivityVoyage extends AppCompatActivity {
    Ecouteur ec;
    ImageButton avion;
    ImageButton hotel;
    Button total;
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    TextView nbreAvion;
    TextView nbreHotel;
    TextView totalVoyage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_voyage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ec = new Ecouteur();

        avion = findViewById(R.id.avion);
        hotel = findViewById(R.id.hotel);
        nbreAvion = findViewById(R.id.qteAvion);

    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source){

        }
    }
}