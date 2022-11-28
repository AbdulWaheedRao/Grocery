package com.example.grocery;

import android.content.Context;
import android.content.Intent;
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

public class PopularAdopter extends RecyclerView.Adapter<PopularAdopter.ViewHolder> {

    private Context context;
    private List<HomeModelClass> popularModelList;

    public PopularAdopter(Context context, List<HomeModelClass> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popularModelList.get(position).getImag_url()).into(holder.ivSale);
        holder.name.setText(popularModelList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DHActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("type",popularModelList.get(holder.getAdapterPosition()).getType());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSale;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSale = itemView.findViewById(R.id.ivSale);
            name = itemView.findViewById(R.id.name);

        }
    }
}
