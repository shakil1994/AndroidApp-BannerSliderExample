package com.example.shakil.androidbannersliderexample.Interface;

import com.example.shakil.androidbannersliderexample.Model.Banner;

import java.util.List;

public interface IBannerLoadListener {
    void onBannerLoadSuccess(List<Banner> banners);
    void onBannerLoadFailed(String message);
}
