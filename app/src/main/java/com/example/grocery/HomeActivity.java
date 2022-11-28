package com.example.grocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    Button btnEnglish, btnUrdu, btnLogin;
    TextView tvTitle, tvDetail;
    ImageView ivPix;
    FirebaseAuth mauth;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mauth = FirebaseAuth.getInstance();

        btnEnglish = findViewById(R.id.btnEnglish);
        btnUrdu = findViewById(R.id.btnUrdu);
        btnLogin = findViewById(R.id.btnLogin);
        tvTitle = findViewById(R.id.tvTitle);
        tvDetail = findViewById(R.id.tvDetail);
        ivPix = findViewById(R.id.ivPix);

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = LocaleHelper.setLocale(HomeActivity.this, "en");
                resources = context.getResources();
                tvTitle.setText(resources.getString(R.string.language));
                tvDetail.setText(resources.getString(R.string.Detail));
                btnLogin.setText(resources.getString(R.string.login));
            }
        });

        btnUrdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = LocaleHelper.setLocale(HomeActivity.this, "ur");
                resources = context.getResources();
                tvTitle.setText(resources.getString(R.string.language));
                tvDetail.setText(resources.getString(R.string.Detail));
                btnLogin.setText(resources.getString(R.string.login));
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NextActivity.class);
                startActivity(intent);
            }
        });
//        btnLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent in = new Intent(HomeActivity.this, MapActivity.class);
//                if (mauth.getCurrentUser() !=null){
//                    btnLocation.setEnabled(false);
//                    startActivity(in);
//                    }else{
//                    Toast.makeText(HomeActivity.this, "First Login then click this button", Toast.LENGTH_SHORT).show();
//                }
//                }
//    });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mauth.getCurrentUser() != null) {
            Intent intent = new Intent(HomeActivity.this, DrawerNavigation.class);
            startActivity(intent);
        }

    }
}
