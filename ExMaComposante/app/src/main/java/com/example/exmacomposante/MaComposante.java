package com.example.exmacomposante;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MaComposante extends ConstraintLayout {
    TextView textView, textView2, textView3;

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

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.activity_main_2, this, true);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.lettre);
        textView3 = findViewById(R.id.textView3);
    }
}
