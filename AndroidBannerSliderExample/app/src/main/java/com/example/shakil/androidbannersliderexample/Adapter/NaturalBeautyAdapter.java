package com.example.shakil.androidbannersliderexample.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shakil.androidbannersliderexample.Model.NaturalBeauty;
import com.example.shakil.androidbannersliderexample.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NaturalBeautyAdapter extends RecyclerView.Adapter<NaturalBeautyAdapter.MyViewHolder> {

    Context context;
    List<NaturalBeauty> naturalBeautyList;

    public NaturalBeautyAdapter(Context context, List<NaturalBeauty> naturalBeautyList) {
        this.context = context;
        this.naturalBeautyList = naturalBeautyList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_natural_beauty, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(naturalBeautyList.get(position).getImage()).into(holder.image_natural_beauty);
    }

    @Override
    public int getItemCount() {
        return naturalBeautyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        KenBurnsView image_natural_beauty;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image_natural_beauty = itemView.findViewById(R.id.image_natural_beauty);
        }
    }
}
