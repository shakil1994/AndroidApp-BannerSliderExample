package com.example.shakil.androidbannersliderexample.Interface;

import com.example.shakil.androidbannersliderexample.Model.Banner;
import com.example.shakil.androidbannersliderexample.Model.NaturalBeauty;

import java.util.List;

public interface INaturalBeautyLoadListener {
    void onNaturalBeautyLoadSuccess(List<NaturalBeauty> naturalBeauties);
    void onNaturalBeautyLoadFailed(String message);
}
