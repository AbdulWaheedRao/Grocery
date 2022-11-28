package com.example.grocery;

import android.content.Context;
import android.content.Intent;
import android.hardware.lights.LightState;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdopter extends RecyclerView.Adapter<CategoryAdopter.ViewHolder> {
    Context context;
    List<CategoryModel> categoryModelList;

    public CategoryAdopter(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(categoryModelList.get(position).getImag_url()).into(holder.ivbottle);
        holder.tvname.setText(categoryModelList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DHActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("type",categoryModelList.get(holder.getAdapterPosition()).getType());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivbottle;
        TextView tvname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivbottle = itemView.findViewById(R.id.ivbottle);
           tvname = itemView.findViewById(R.id.tvname);
        }
    }
}
