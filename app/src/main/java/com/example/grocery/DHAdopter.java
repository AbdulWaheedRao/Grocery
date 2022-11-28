package com.example.grocery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DHAdopter extends RecyclerView.Adapter<DHAdopter.ViewHolder> {
    Context context;
    List<DHModel> dhModelList;

    public DHAdopter(Context context, List<DHModel> dhModelList) {
        this.context = context;
        this.dhModelList = dhModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dh_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(dhModelList.get(position).getImag_url()).into(holder.ivdemo);
        holder.tvname.setText(dhModelList.get(position).getName());
        holder.tvprice.setText(Integer.toString(dhModelList.get(position).getPrice()));
        holder.tvml.setText(dhModelList.get(position).getWeight());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailedActivity.class);
                intent.putExtra("detail",dhModelList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dhModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivdemo;
        TextView tvname, tvprice,tvml;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivdemo = itemView.findViewById(R.id.ivdemo);
            tvname = itemView.findViewById(R.id.tvname);
            tvprice = itemView.findViewById(R.id.tvprice);
            tvml=itemView.findViewById(R.id.tvml);

        }
    }
}
