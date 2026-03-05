package com.example.tp1_annexe1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jetbrains.annotations.NonNls;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout parent;
    Surface s;
    Button enter;
    EditText entreX;
    EditText entreY;
    Point depart, arrivee;
    Ecouteur ec;
    int x, y;
    Path p;

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

        parent = findViewById(R.id.parent);

        ec = new Ecouteur();

        enter.findViewById(R.id.button);
        enter.setOnClickListener(ec);

        s = new Surface(this);
        s.setBackgroundColor(Color.RED);
        s.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));
        parent.addView(s);
        p = new Path();
    }

    private class Ecouteur implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            x = Integer.parseInt(entreX.getText().toString());
            y = Integer.parseInt(entreY.getText().toString());
            if (p.isEmpty()){

            }
        }
    }

    private class Surface extends View {
        public Surface(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
        }
    }
}