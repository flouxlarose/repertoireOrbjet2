package com.example.tpfinal;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MaComposante extends ConstraintLayout {
    TextView textBonus, textLettre, textPoints;

    public MaComposante(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MaComposante(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MaComposante(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void changerType(Lettre lettre){
        textLettre.setText(String.valueOf(lettre.getLettre()));
        textPoints.setText(String.valueOf(lettre.getValeur()));

        switch (lettre.getBonus()){
            case NONE:
                textBonus.setText("");
                break;
            case DOUBLE:
                textBonus.setText("x2");
                textBonus.setTextColor(Color.GREEN);
                break;
            case TRIPLE:
                textBonus.setText("x3");
                textBonus.setTextColor(Color.GREEN);
                break;
            case MOT_DOUBLE:
                textBonus.setText("x2MOT");
                textBonus.setTextColor(Color.RED);
                break;
        }
    }
    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.lettre, this, true);
        textBonus = findViewById(R.id.bonus);
        textLettre = findViewById(R.id.lettre);
        textPoints = findViewById(R.id.points);
    }
}

