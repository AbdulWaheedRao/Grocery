package com.example.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView ivpic;
    TextView tvprice, tvquantity;
    ImageView ivplus, ivsub;
    Button btncart;
    int totalPrice = 0;
    DHModel dhModel = null;
    int totalquantity = 1;
    FirebaseFirestore firestore;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Toolbar toolbar = findViewById(R.id.tbtitle);
        toolbar.isShown();

        mauth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof DHModel) {
            dhModel = (DHModel) object;
        }
        tvquantity = findViewById(R.id.tvquantity);
        ivpic = findViewById(R.id.ivpic);
        tvprice = findViewById(R.id.tvprice);
        ivplus = findViewById(R.id.ivplus);
        ivsub = findViewById(R.id.ivsub);

        if (dhModel != null) {
            Glide.with(getApplicationContext()).load(dhModel.getImag_url()).into(ivpic);
            tvprice.setText("Rs" + Integer.toString(dhModel.getPrice()));
        }


        btncart = findViewById(R.id.btncart);
        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });


        ivplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalquantity < 10) {
                    totalquantity++;
                    tvquantity.setText(String.valueOf(totalquantity));
                    totalPrice = dhModel.getPrice() * totalquantity;
                }

            }
        });
        ivsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalquantity > 0) {
                    totalquantity--;
                    tvquantity.setText(String.valueOf(totalquantity));
                    totalPrice = dhModel.getPrice() * totalquantity;
                }

            }
        });

    }

    private void addedToCart() {

        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd ,yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("productname", dhModel.getName());
        cartMap.put("productprice", tvprice.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalquantity", tvquantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        firestore.collection("CurrentUser").document(mauth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this, "Added to a cart Successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }
}