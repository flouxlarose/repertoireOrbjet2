package com.example.atelier3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class maComposante extends ConstraintLayout {

    TextView textView1, textView2, textView3;

    public maComposante(@NonNull Context context) {
        super(context);
        init(context);
    }

    public maComposante(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public maComposante(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        // instancier un élément de cette classe à partir de son fichierLayoutInflater.from(context).inflate(R.layout.nom_de_votre_fichier_xml, this, true);
        LayoutInflater.from(context).inflate(R.layout.composant, this, true);
        // initialiser les composants internes avec findViewById, autres initialisations
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
    }

}

