package com.example.shakil.kotlinbannerslider.Adapter

import com.example.shakil.kotlinbannerslider.Model.Banner
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class HomeSliderAdapter(internal var bannerLists: List<Banner>) : SliderAdapter(){

    override fun getItemCount(): Int {
        return bannerLists.size
    }

    override fun onBindImageSlide(position: Int, imageSlideViewHolder: ImageSlideViewHolder?) {
        imageSlideViewHolder!!.bindImageSlide(bannerLists[position].image)
    }

}