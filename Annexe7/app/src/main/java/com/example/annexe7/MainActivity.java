package com.example.annexe7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

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
    ConstraintLayout main;

    Point depart, arrivee;
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

        // ecouteur
        ec = new Ecouteur();


        // 1
        surf = new SurfaceDessin(this);
        // 2
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1 , -1));
        // 3
        main.addView(surf);
        surf.setOnTouchListener(ec);
    }

    private class Ecouteur implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                depart = new Point((int)motionEvent.getX(), (int) motionEvent.getY());
                surf.invalidate();  // redessiner --> efface et appel la méthode onDraw
            } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                arrivee = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
                surf.invalidate();  // redessiner --> efface et appel la méthode onDraw
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                depart = null;
                arrivee = null;
            }

            // important!
            return true;
        }
    }

    private class SurfaceDessin extends View {
        private Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        public SurfaceDessin (Context context){
            super(context);
            this.setBackgroundResource(R.drawable.carte);
            p.setColor(Color.RED);
            p.setStrokeWidth(15);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas){
            super.onDraw(canvas);
            if (depart != null){
                canvas.drawRect(depart.x-30, depart.y+30, depart.x+30, depart.y-30, p);
            }

            if (arrivee != null){
                canvas.drawRect(arrivee.x-30, arrivee.y+30, arrivee.x+30, arrivee.y-30, p);
                canvas.drawLine(depart.x, depart.y, arrivee.x, arrivee.y, p);
            }
        }
    }
}