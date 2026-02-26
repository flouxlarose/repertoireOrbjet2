
package com.example.annexe6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

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
        // surf.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
         surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1 , -1));   // la constante MATCH_PARENT vaut -1 donc c'est equivalent mais moins explicite
//         surf.setLayoutParams(new ConstraintLayout.LayoutParams(convertirDpEnPX(200), convertirDpEnPX(200)));
        // 3
        main.addView(surf);
    }

    public int convertirDpEnPX(int dp){
        float densite = this.getResources().getDisplayMetrics().density;
        return Math.round(densite * dp);
    }

    private class SurfaceDessin extends View {

        Paint crayon;
        Paint crayon2;
        Paint crayon3a;
        Paint crayon3b;
        Paint crayon3c;

        public SurfaceDessin(Context context){
            super(context);
            this.setBackgroundColor(Color.GRAY);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setColor(Color.GREEN);

            crayon2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon2.setStyle(Paint.Style.STROKE);
            crayon2.setStrokeWidth(10);
            crayon2.setColor(Color.YELLOW);

            crayon3a = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon3a.setColor(Color.RED);

            crayon3b = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon3b.setColor(Color.BLUE);

            crayon3c = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon3c.setColor(Color.YELLOW);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas){
            super.onDraw(canvas);
            canvas.drawCircle(100, 100, 80, crayon);
            canvas.drawCircle(270, 100, 80, crayon2);
            canvas.drawArc(400, 400, 600, 600, 0, 120, true, crayon3a);
            canvas.drawArc(400, 400, 600, 600, 120, 120, true, crayon3b);
            canvas.drawArc(400, 400, 600, 600, 240, 120, true, crayon3c);
        }
    }
}