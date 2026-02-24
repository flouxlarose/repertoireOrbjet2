package com.example.annexe2;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationBarView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Button boutonEnvoyer;
    Spinner spinnerNom;
    EditText champDestinataire;
    EditText champMontant;
    TextView champSolde;

    DecimalFormat df = new DecimalFormat("#,##0.00$");

    HashMap<String, Compte> hm = new HashMap<>();

    ArrayList<String> choix;
    Compte compteChoisi;
    int solde;

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

        boutonEnvoyer = findViewById(R.id.envoyer);
        spinnerNom = findViewById(R.id.spinnerNom);
        champSolde = findViewById(R.id.solde);
        champDestinataire = findViewById(R.id.Receveur);
        champMontant = findViewById(R.id.montant);

        choix = new ArrayList<>();

        hm.put("Chèques", new Compte("Chèques", 1000));
        hm.put("Épargne", new Compte("Épargne", 200));
        hm.put("Épargne plus", new Compte("Épargne plus", 490));

        choix.addAll(hm.keySet());  //place toutes les clés dans le spinner

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, choix);
        spinnerNom.setAdapter(adapter);
        // étape 1
        ec = new Ecouteur();

        // étape 2
        boutonEnvoyer.setOnClickListener(ec);
        spinnerNom.setOnItemSelectedListener(ec);
    }

    // étape 3
    private class Ecouteur implements View.OnClickListener, AdapterView.OnItemSelectedListener
    {
        @Override
        public void onClick(View source) {
            if (source == boutonEnvoyer){
//                String destinataire = champDestinataire.getText().toString();
//                String temp = champMontant.getText().toString();
//                int montant = Integer.parseInt(temp);

                String courriel = String.valueOf(champDestinataire.getText());
                courriel = courriel.trim();

                if(!courriel.isEmpty()){
                    String montant = String.valueOf(champMontant.getText().toString());
                    int transfert = Integer.parseInt(montant);
                    if (compteChoisi.transfert(transfert)){
                        champSolde.setText(df.format((compteChoisi.getSolde())));
                        champMontant.setText("");
                    }
                    else {   //transfert trop grand
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("attention")
                                .setMessage("il manque des fonds")
                                .setPositiveButton("ok", null);
                        builder.create().show();
                    }
                }
                else {
                    champDestinataire.setText("Vous devez indiquer un courriel");
                    champMontant.setText("0");
                }

//                if (destinataire.matches("^[^@\\s]+@[^@\\s]+\\.com$") && solde >= montant){
//                    // est un courriel
//                    solde -= montant;
//                    champSolde.setText(df.format(String.valueOf(solde)));
//                    champDestinataire.setText("");
//                    champMontant.setText("");
//                }
//                else{
//                    champDestinataire.setText("invalide");
//                    champMontant.setText("invalide");
//                }
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            // méthode a moi zebi
            String texte1 = parent.getSelectedItem().toString();

//            //méthode jacob
//            String texte2 = parent.getAdapter().getItem(position).toString();
//
//            //méthode emile-eric
//            String texte3 = choix.get(position);
//
//            //méthode eric
//            TextView temp = (TextView) view;
//            String texte4 = temp.getText().toString();
//
//            //méthode 5
//            String texte5 = (String) parent.getItemAtPosition(position);

            Toast.makeText(MainActivity.this, texte1, Toast.LENGTH_SHORT).show();

            //trouver le compte si je connais la cle (nom du comtpe)
            compteChoisi = hm.get(texte1);

            //afficher le solde du compte dans le champSolde
            champSolde.setText(df.format(compteChoisi.getSolde()));

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}