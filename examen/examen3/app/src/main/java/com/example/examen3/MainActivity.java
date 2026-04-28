package com.example.examen3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GestionBD instance;
    ListView spinner;
    Ecouteur ec;
    ArrayList<String> listeCegep;
    Activity DepartActivity;


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
        spinner = findViewById(R.id.spinnerCegep);
        instance = GestionBD.getInstance(getApplicationContext());
        instance.ouvrirConnexionBD();
        instance.insert();

        // remplir le spinner
        listeCegep = instance.nomEquipes();
        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listeCegep);
        spinner.setAdapter(aa);

        Ecouteur ec = new Ecouteur();
        spinner.setOnItemClickListener(ec);
    }
    private class Ecouteur implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View itemClique, int position, long id){
            Intent i = new Intent(MainActivity.this, MainActivity2.class);
            System.out.println(itemClique.toString());
            i.putExtra("cle1", itemClique.toString());
            startActivity(i);
        }
    }
}