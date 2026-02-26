package com.example.annexe7;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SurfaceDessin surf;
    ConstraintLayout main;
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

        main = findViewById(R.id.main);

        // 1
        surf = new SurfaceDessin(this);
        // 2
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1 , -1));
        // 3
        main.addView(surf);
    }

    private class SurfaceDessin extends View {

        public SurfaceDessin (Context context){
            super(context);
            this.setBackgroundResource(R.drawable.carte);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas){
            super.onDraw(canvas);
        }
    }
}