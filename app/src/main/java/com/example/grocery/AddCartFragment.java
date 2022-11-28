package com.example.grocery;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddCartFragment extends Fragment {

    RecyclerView recyclerview;
    MyCartAdopter myCartAdopter;
    List<MyCartModel> myCartModelList;
    FirebaseFirestore db;
    FirebaseAuth mauth;
    TextView textview;
    Button btnbuy;

    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE = 100;
    Button btnNext;
    EditText etAdress;
    Context context;


    public AddCartFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_addcart, container, false);
        db = FirebaseFirestore.getInstance();
        mauth = FirebaseAuth.getInstance();
        recyclerview = root.findViewById(R.id.recyclerview);
        textview = root.findViewById(R.id.textview);
        btnbuy = root.findViewById(R.id.btnbuy);
        btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlaceOrderActivity.class);
                intent.putExtra("itemList", (Serializable) myCartModelList);
                startActivity(intent);
            }
        });

//        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, new IntentFilter("Mytotalamount"));


        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myCartModelList = new ArrayList<>();
        myCartAdopter = new MyCartAdopter(getActivity(), myCartModelList);
        recyclerview.setAdapter(myCartAdopter);


        db.collection("CurrentUser").document(mauth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        myCartModelList.clear();
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                String doucmentId = documentSnapshot.getId();

                                MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);
                                cartModel.setDoucmentId(doucmentId);

                                myCartModelList.add(cartModel);
                                myCartAdopter.notifyDataSetChanged();
                            }
                        }
                        calculateTotalAmount();
                    }
                });

        return root;

    }


//    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            int totalbill = intent.getIntExtra("totalamount", 0);
//            textview.setText("Total bill:" + totalbill + "Rs");
//
//        }
//    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnNext = view.findViewById(R.id.btnNext);
        etAdress = view.findViewById(R.id.etAdress);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        //        Location

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
                Log.d("click", "onClick: ");
            }
        });
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                                List<Address> address;
                                try {
                                    address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    etAdress.setText(address.get(0).getAddressLine(0));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(getContext(), "null location", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } else {
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions((Activity) getContext(), new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(getContext(), "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void calculateTotalAmount() {
        int totalAmount = 0;
        for (MyCartModel cartModel : myCartModelList) {
            totalAmount += cartModel.getTotalPrice();
        }
        textview.setText("PKR:" + totalAmount);
    }
}
