package com.example.grocery;

import android.hardware.lights.LightState;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseFirestore db;
    List<HomeModelClass> homeModelClassList;
    PopularAdopter popularAdopter;

    RecyclerView recycler;
    CategoryAdopter categoryAdopter;
    List<CategoryModel> categoryModelList;

    RecyclerView recycle;
    RecommendedAdopter recommendedAdopter;
    List<RecommendedModel> recommendedModelList;

    List<DHModel> dhModelList;
    RecyclerView recyclerViewsearch;
    DHAdopter dhAdopter;
    EditText etsearch;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db = FirebaseFirestore.getInstance();
        recyclerView = root.findViewById(R.id.recyclerview);
        recycler = root.findViewById(R.id.recycler);
        recycle = root.findViewById(R.id.recycle);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        homeModelClassList = new ArrayList<>();
        popularAdopter = new PopularAdopter(getActivity(), homeModelClassList);
        recyclerView.setAdapter(popularAdopter);
        recyclerView.setHasFixedSize(true);

        db.collection("PopularProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeModelClass homeModelClass = document.toObject(HomeModelClass.class);
                                homeModelClassList.add(homeModelClass);
                                popularAdopter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categoryModelList = new ArrayList<>();
        categoryAdopter = new CategoryAdopter(getActivity(), categoryModelList);
        recycler.setAdapter(categoryAdopter);
        recycler.setHasFixedSize(true);

        db.collection("Category Product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdopter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        recycle.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recommendedModelList = new ArrayList<>();
        recommendedAdopter = new RecommendedAdopter(getActivity(), recommendedModelList);
        recycle.setAdapter(recommendedAdopter);
        recycle.setHasFixedSize(true);

        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                recommendedAdopter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        etsearch = root.findViewById(R.id.etsearch);

        recyclerViewsearch = root.findViewById(R.id.recyclerViewsearch);
        dhModelList = new ArrayList<>();
        dhAdopter = new DHAdopter(getContext(), dhModelList);
        recyclerViewsearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewsearch.setAdapter(dhAdopter);
        recyclerViewsearch.setHasFixedSize(true);
        etsearch.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().isEmpty()) {
                    dhModelList.clear();
                    dhAdopter.notifyDataSetChanged();
                } else {
                    searchProduct(s.toString());
                }
            }
        });


        return root;
    }

    private void searchProduct(String type) {
        if (!type.isEmpty()) {

            db.collection("AllProduct").whereEqualTo("type", type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                dhModelList.clear();
                                dhAdopter.notifyDataSetChanged();
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    DHModel dhModel = documentSnapshot.toObject(DHModel.class);
                                    dhModelList.add(dhModel);
                                    dhAdopter.notifyDataSetChanged();
                                }

                            }
                        }
                    });

        }
    }
}