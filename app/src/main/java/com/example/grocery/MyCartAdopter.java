package com.example.grocery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyCartAdopter extends RecyclerView.Adapter<MyCartAdopter.ViewHolder> {

    Context context;
    List<MyCartModel> myCartModelList;
    int totalPrice = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;


    public MyCartAdopter(Context context, List<MyCartModel> myCartModelList) {
        this.context = context;
        this.myCartModelList = myCartModelList;

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.addcart_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ProductName.setText(myCartModelList.get(position).getProductname());
        holder.ProductPrice.setText(myCartModelList.get(position).getProductprice());
        holder.CurrentDate.setText(myCartModelList.get(position).getCurrentDate());
        holder.CurrentTime.setText(myCartModelList.get(position).getCurrentTime());
        holder.TotalQuantity.setText(myCartModelList.get(position).getTotalquantity());
        holder.TotalPrice.setText(String.valueOf(myCartModelList.get(position).getTotalPrice()));



//            totalPrice = totalPrice + myCartModelList.get(position).getTotalPrice();
//            Intent intent = new Intent("Mytotalamount");
//            intent.putExtra("totalPrice", totalPrice);
//            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);



        holder.ivdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart")
                        .document(myCartModelList.get(position).getDoucmentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    myCartModelList.remove(myCartModelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(context, "error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }

    @Override
    public int getItemCount() {
        return myCartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ProductName, ProductPrice, CurrentDate, CurrentTime, TotalQuantity, TotalPrice;
        ImageView ivdelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ProductName = itemView.findViewById(R.id.ProductName);
            ProductPrice = itemView.findViewById(R.id.ProductPrice);
            CurrentDate = itemView.findViewById(R.id.CurrentDate);
            CurrentTime = itemView.findViewById(R.id.CurrentTime);
            TotalQuantity = itemView.findViewById(R.id.TotalQuantity);
            TotalPrice = itemView.findViewById(R.id.TotalPrice);
            ivdelete = itemView.findViewById(R.id.ivdelete);
        }
    }
}
