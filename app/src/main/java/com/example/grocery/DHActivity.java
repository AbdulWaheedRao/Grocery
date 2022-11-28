package com.example.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DHActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    DHAdopter dhAdopter;
    List<DHModel> dhModelList;
    FirebaseFirestore firestore;
    String context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dhactivity);

        Toolbar toolbar=findViewById(R.id.tbtitle);
        toolbar.isShown();


        recyclerview = findViewById(R.id.recyclerview);
        firestore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");


        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        dhModelList = new ArrayList<>();
        dhAdopter = new DHAdopter(this, dhModelList);
        recyclerview.setAdapter(dhAdopter);


        if (type != null && type.equalsIgnoreCase("fruit")) {
            firestore.collection("AllProduct").whereEqualTo("type", "fruit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }


        if (type != null && type.equalsIgnoreCase("vegetable")) {
            firestore.collection("AllProduct").whereEqualTo("type", "vegetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });

        }

        if (type != null && type.equalsIgnoreCase("meat")) {
            firestore.collection("AllProduct").whereEqualTo("type", "meat").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });

        }

        if (type != null && type.equalsIgnoreCase("groceryitem")) {
            firestore.collection("AllProduct").whereEqualTo("type", "groceryitem").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }

        if (type != null && type.equalsIgnoreCase("fish")) {
            firestore.collection("AllProduct").whereEqualTo("type", "fish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }

        if (type != null && type.equalsIgnoreCase("milk")) {
            firestore.collection("AllProduct").whereEqualTo("type", "milk").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }

        if (type != null && type.equalsIgnoreCase("frui")) {
            firestore.collection("AllProduct").whereEqualTo("type", "frui").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }

        if (type != null && type.equalsIgnoreCase("vege")) {
            firestore.collection("AllProduct").whereEqualTo("type", "vege").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }

        if (type != null && type.equalsIgnoreCase("tissue")) {
            firestore.collection("AllProduct").whereEqualTo("type", "tissue").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }

        if (type != null && type.equalsIgnoreCase("pamper")) {
            firestore.collection("AllProduct").whereEqualTo("type", "pamper").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }


        if (type != null && type.equalsIgnoreCase("flours")) {
            firestore.collection("AllProduct").whereEqualTo("type", "flours").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }

        if (type != null && type.equalsIgnoreCase("flour")) {
            firestore.collection("AllProduct").whereEqualTo("type", "flour").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }

        if (type != null && type.equalsIgnoreCase("product")) {
            firestore.collection("AllProduct").whereEqualTo("type", "product").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }

        if (type != null && type.equalsIgnoreCase("oil")) {
            firestore.collection("AllProduct").whereEqualTo("type", "oil").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }

        if (type != null && type.equalsIgnoreCase("coffee")) {
            firestore.collection("AllProduct").whereEqualTo("type", "coffee").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot document : task.getResult()) {
                        DHModel dhModel = document.toObject(DHModel.class);
                        dhModelList.add(dhModel);
                        dhAdopter.notifyDataSetChanged();

                    }

                }
            });
        }


    }

}
