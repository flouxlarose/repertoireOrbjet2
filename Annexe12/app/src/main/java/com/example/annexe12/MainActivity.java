package com.example.annexe12;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GestionBD instance;
    ListView choix;
    TextView question;
    TextView reponse;
    Ecouteur ec;
    ArrayList<String> choixDeReponse;
    boolean essai = false;

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
        choix = findViewById(R.id.choix);
        question = findViewById(R.id.question);
        reponse = findViewById(R.id.reponse);
        question.setText("Mary Anderson est reconnue pour avoir inventer ?");
        reponse.setText("");

        instance = GestionBD.getInstance(getApplicationContext());
        System.out.println(instance.retournerInventions().size());
        instance.ouvrirConnexionBD();

        // remplir le spinner
        choixDeReponse = instance.retournerInventions();
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, choixDeReponse);
        choix.setAdapter(aa);
        Ecouteur ec = new Ecouteur();
        choix.setOnItemClickListener(ec);

    }

    private class Ecouteur implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View itemClique, int position, long id) {
            // String reponseDonnee = (String) choix.getItemAtPosition(position);
            // String reponseDonnee = ((TextView)itemClique).getText().toString();
            if (!essai){
                essai = true;
                String reponseDonnee = choixDeReponse.get(position);

                if (instance.aBonneReponse("Mary Anderson", reponseDonnee)){
                    choix.getChildAt(position).setBackgroundColor(Color.GREEN);
                    reponse.setText("BONNE REPONSE !");
                }
                else{
                    reponse.setText("MAUVAISE REPONSE");
                    choix.getChildAt(position).setBackgroundColor(Color.RED);
                    try {
                        choix.getChildAt(instance.trouverIndiceBonneReponse("Mary Anderson")).setBackgroundColor(Color.GREEN);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }


        }
    }
}