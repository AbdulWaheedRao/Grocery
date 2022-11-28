package com.example.grocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {
    Button btnArrow, btnPhone;
    TextView tvConncet, tvText, tvData;
    ImageView ivMobile;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        btnArrow = findViewById(R.id.btnArrow);
        btnPhone = findViewById(R.id.btnPhone);
        tvConncet = findViewById(R.id.tvConnect);
        tvText = findViewById(R.id.tvText);
        tvData = findViewById(R.id.tvData);
        ivMobile = findViewById(R.id.ivMobile);
        btnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NextActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(NextActivity.this,LoginActivity.class);
                startActivity(inte);
            }
        });

    }
}