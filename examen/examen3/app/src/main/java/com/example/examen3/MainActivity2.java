package com.example.examen3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    GestionBD instance;
    TextView info1;
    TextView info2;
    TextView info3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        instance = GestionBD.getInstance(getApplicationContext());

        String nomCegep = getIntent().getStringExtra("cle1");
        System.out.println(nomCegep);

        Cegep c1 = instance.cegepSelonNom(nomCegep);
        info1 = findViewById(R.id.nomCegep);
        info2 = findViewById(R.id.nbreEquipe);
        info1 = findViewById(R.id.adresse);

        info1.setText(c1.getNomEquipe());
        info2.setText("nombre d'équipes : " + c1.getNbreEquipe());
        info3.setText(c1.getAdresse());



    }
}