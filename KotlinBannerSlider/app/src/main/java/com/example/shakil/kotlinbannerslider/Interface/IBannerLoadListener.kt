package com.example.shakil.kotlinbannerslider.Interface

import com.example.shakil.kotlinbannerslider.Model.Banner

interface IBannerLoadListener {
    fun onBannerLoadSuccess(banners: List<Banner>)
    fun onBannerLoadFailed(message: String)
}