package com.example.shakil.kotlinbannerslider.Interface

import com.example.shakil.kotlinbannerslider.Model.NaturalBeauty

interface INaturalBeautyLoadListener {
    fun onNaturalBeautyLoadSuccess(naturalBeauties: List<NaturalBeauty>)
    fun onNaturalBeautyLoadFailed(message: String)
}