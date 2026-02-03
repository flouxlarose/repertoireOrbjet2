package com.example.annexe2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Button boutonValider;
    Button boutonEnvoyer;
    EditText champNomCompte;
    EditText champDestinataire;
    EditText champMontant;
    TextView champSolde;

    ArrayList<String> choix;

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

        boutonValider = findViewById(R.id.bouttonValider);
        boutonEnvoyer = findViewById(R.id.envoyer);
        champNomCompte = findViewById(R.id.compte);
        champSolde = findViewById(R.id.solde);
        champDestinataire = findViewById(R.id.Receveur);
        champMontant = findViewById(R.id.montant);

        choix = new ArrayList<>();
        choix.add("CHEQUE");
        choix.add("EPARGNE");
        choix.add("EPARGNEPLUS");

        // étape 1
        ec = new Ecouteur();

        // étape 2
        boutonValider.setOnClickListener(ec);
        boutonEnvoyer.setOnClickListener(ec);

    }

    // étape 3
    private class Ecouteur implements View.OnClickListener
    {
        @Override
        public void onClick(View source) {
            if (source == boutonValider){
                // quand on click on arrive ici
                String nomCompte = champNomCompte.getText().toString();
                // autre maniere de faire:
                // String nomCompte = String.valueOf((champNomCompte.getText()));
                nomCompte = nomCompte.trim().toUpperCase();

                if (choix.contains(nomCompte)){
                    solde = 500;
                    champSolde.setText(String.valueOf(solde));
                }

                else {
                    champSolde.setText("pas un bon nom");
                    champNomCompte.setText("");
                }
            }
            else if (source == boutonEnvoyer){
                String destinataire = champDestinataire.getText().toString();
                String temp = champMontant.getText().toString();
                int montant = Integer.parseInt(temp);

                if (destinataire.matches("^[^@\\s]+@[^@\\s]+\\.com$") && solde >= montant){
                    // est un courriel
                    solde -= montant;
                    champSolde.setText(String.valueOf(solde));
                    champDestinataire.setText("");
                    champMontant.setText("");
                }
                else{
                    champDestinataire.setText("invalide");
                    champMontant.setText("invalide");
                }
            }
        }
    }
}