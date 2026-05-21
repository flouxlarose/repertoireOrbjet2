package com.example.atelier5_tpf;

import static android.view.animation.Animation.REVERSE;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    View v;
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
        v = findViewById(R.id.view1);
        ObjectAnimator oa = ObjectAnimator.ofFloat(v, View.X, 230, 700);
        oa.setDuration(1000);
        oa.setRepeatCount(8);
        oa.setRepeatMode(REVERSE);
        oa.start();
    }
}