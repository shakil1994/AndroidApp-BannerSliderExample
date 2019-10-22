package com.example.shakil.kotlinbannerslider.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shakil.kotlinbannerslider.Model.NaturalBeauty
import com.example.shakil.kotlinbannerslider.R
import com.flaviofaria.kenburnsview.KenBurnsView
import com.squareup.picasso.Picasso

class NaturalBeautyAdapter(internal var context: Context, internal var naturalBeautyList: List<NaturalBeauty>)
    : RecyclerView.Adapter<NaturalBeautyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_natural_beauty, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return naturalBeautyList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(naturalBeautyList[position].image).into(holder.image_natural_beauty)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var image_natural_beauty: KenBurnsView

        init {
            image_natural_beauty = itemView.findViewById(R.id.image_natural_beauty)
        }

    }
}