package com.example.tp1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Ecouteur ec;
    SurfaceDessin surf;
    LinearLayout draw;

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
        draw = findViewById(R.id.draw);

        surf = new SurfaceDessin(this);
        surf.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        draw.addView(surf);


    }

    private class Ecouteur implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent){
            return true;
        }
    }

    private class SurfaceDessin extends View {
        Paint crayon;

        public SurfaceDessin (Context context){
            super(context);
            this.setBackgroundColor(Color.WHITE);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setColor(Color.RED);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas){
            super.onDraw(canvas);
        }
    }
}