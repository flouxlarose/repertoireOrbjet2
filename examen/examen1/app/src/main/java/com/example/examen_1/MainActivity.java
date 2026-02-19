package com.example.examen_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Ecouteur ec;

    TextView matricule;
    TextView nbreService;
    Button service;
    Button enregistrer;
    TextView nbreEtu;
    TextView meilleurEtu;
    Groupe grp = new Groupe();

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

        matricule = findViewById(R.id.matricule);
        nbreService = findViewById(R.id.nbreServices);
        service = findViewById(R.id.bouttonService);
        enregistrer = findViewById(R.id.bouttonEnregistrer);
        nbreEtu = findViewById(R.id.nbreEtu);
        meilleurEtu = findViewById(R.id.meilleurEtu);

        ec = new Ecouteur();

        service.setOnClickListener(ec);
        enregistrer.setOnClickListener(ec);


    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick (View source){
            if (source == service){
                if (nbreService.getText().toString().isEmpty()){
                    nbreService.setText("1");
                }
                else {
                    int temp = Integer.parseInt(nbreService.getText().toString());
                    temp += 1;
                    nbreService.setText(String.valueOf(temp));
                }

            } else if (source == enregistrer) {
                if (matricule.getText().toString().matches("\\d{7}")){
                    int mat = Integer.parseInt(matricule.getText().toString());
                    int ser = Integer.parseInt(nbreService.getText().toString());
                    Evaluation etudiant = new Evaluation(mat, ser);
                    grp.addEtu(etudiant);

                    matricule.setText("");
                    nbreService.setText("");
                    nbreEtu.setText(String.valueOf(grp.nbreEtu()));
                    meilleurEtu.setText(String.valueOf(grp.meilleurMatricule()));
                    Toast.makeText(MainActivity.this, "L'étudiant a été enregistré !", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Le matricule doit contenir 7 chiffre", Toast.LENGTH_SHORT).show();
                    matricule.setText("");
                }
//                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        }
    }

}