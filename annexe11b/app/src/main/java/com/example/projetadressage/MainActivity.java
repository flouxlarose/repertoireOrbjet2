package com.example.projetadressage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.StringJoiner;

import bla.HashtableAssociation;


public class MainActivity extends AppCompatActivity {

    EditText champPrenom, champNom, champAdresse, champZip;
    Spinner spinnerCapitale, spinnerEtat;

    Button bouton;

    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        champPrenom = findViewById(R.id.champPrenom);
        champNom= findViewById(R.id.champNom);
        champAdresse = findViewById(R.id.champAdresse);
        champZip = findViewById(R.id.champZip);

        spinnerCapitale = findViewById(R.id.spinnerCapitale);
        spinnerEtat = findViewById(R.id.spinnerEtat);

        bouton = findViewById(R.id.boutonInscrire);


        // remplir les spinner à l'aide de la Hashtable
        HashtableAssociation h = new HashtableAssociation();
        ArrayList<String> choix = new ArrayList<>();
        choix.addAll(h.keySet());
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, choix);
        spinnerCapitale.setAdapter(aa);

        HashtableAssociation h2 = new HashtableAssociation();
        ArrayList<String> choix2 = new ArrayList<>();
        choix2.addAll(h2.values());
        ArrayAdapter aa2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, choix2);
        spinnerEtat.setAdapter(aa2);

        Ecouteur ec = new Ecouteur();
        bouton.setOnClickListener(ec);
    }
    private class Ecouteur implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            try {
                Inscrit i = new Inscrit(champNom.getText().toString(),
                        champPrenom.getText().toString(),
                        champAdresse.getText().toString(),
                        (String)spinnerCapitale.getSelectedItem(),
                        (String)spinnerEtat.getSelectedItem(),
                        champZip.getText().toString());
                        creerAlertDialog("Électeur inscrit", "inscription");
            }
            catch (AdresseException ae){
                creerAlertDialog(ae.getMessage(), "erreur");
            }
        }
        public void creerAlertDialog(String message, String titre){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            // on peut faire ca
            builder.setMessage(message)
                    .setTitle(titre);

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}