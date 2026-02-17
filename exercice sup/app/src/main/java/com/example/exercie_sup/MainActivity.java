package com.example.exercie_sup;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Ecouteur ec;
    LinearLayout mainLayout;

    TextView password;

    String codeValide = "123456";

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

        mainLayout = findViewById(R.id.main);

        password = findViewById(R.id.editTextTextPassword);

        // 1ere etape
        ec = new Ecouteur();
        // 2e etape
        for ( int i = 0; i < mainLayout.getChildCount(); i++){
            if(mainLayout.getChildAt(i) instanceof LinearLayout){       // on exlu le dernier enfant car on ne veux pas mettre d'écouteur dessus
                LinearLayout temp = (LinearLayout) mainLayout.getChildAt(i);
                for (int j = 0; j < temp.getChildCount(); j++){
                    if (temp.getChildAt(j) instanceof Button) {
                        temp.getChildAt(j).setOnClickListener(ec);
                    }

                }
            }
        }
    }

    // 3e etape
    private class Ecouteur implements View.OnClickListener
    {

        @Override
        public void onClick (View source){
            String temp = password.getText().toString();
            temp += ((Button) source).getText();
            password.setText(temp);
            if (password.length() == codeValide.length()){
                if (password.getText().toString().equals(codeValide)){
                    mainLayout.setBackgroundColor(Color.GREEN);
                }
                else {
                    mainLayout.setBackgroundColor(Color.RED);
                }
            }

        }
    }
}