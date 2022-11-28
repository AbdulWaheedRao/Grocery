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

public class RecommendedAdopter extends RecyclerView.Adapter<RecommendedAdopter.ViewHolder> {
    Context context;
    List<RecommendedModel> recommendedModelList;

    public RecommendedAdopter(Context context, List<RecommendedModel> recommendedModelList) {
        this.context = context;
        this.recommendedModelList = recommendedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(recommendedModelList.get(position).getImag_url()).into(holder.ivsale);
        holder.tvname.setText(recommendedModelList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DHActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("type",recommendedModelList.get(holder.getAdapterPosition()).getType());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recommendedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivsale;
        TextView tvname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivsale=itemView.findViewById(R.id.ivsale);
            tvname=itemView.findViewById(R.id.tvname);
        }
    }
}
