package com.example.shakil.androidbannersliderexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.Slide;
import android.widget.Toast;

import com.example.shakil.androidbannersliderexample.Adapter.HomeSliderAdapter;
import com.example.shakil.androidbannersliderexample.Adapter.NaturalBeautyAdapter;
import com.example.shakil.androidbannersliderexample.Interface.IBannerLoadListener;
import com.example.shakil.androidbannersliderexample.Interface.INaturalBeautyLoadListener;
import com.example.shakil.androidbannersliderexample.Model.Banner;
import com.example.shakil.androidbannersliderexample.Model.NaturalBeauty;
import com.example.shakil.androidbannersliderexample.Service.PicassoImageLoadingService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class MainActivity extends AppCompatActivity implements IBannerLoadListener, INaturalBeautyLoadListener {

    Slider banner_slider;
    RecyclerView recucler_nature;

    //FireStore
    CollectionReference bannerRef, naturalRef;

    //Interface
    IBannerLoadListener iBannerLoadListener;
    INaturalBeautyLoadListener iNaturalBeautyLoadListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banner_slider = findViewById(R.id.banner_slider);
        recucler_nature = findViewById(R.id.recycler_nature);

        iBannerLoadListener = this;
        iNaturalBeautyLoadListener = this;

        bannerRef = FirebaseFirestore.getInstance().collection("Banner");
        naturalRef = FirebaseFirestore.getInstance().collection("NaturalBeauty");

        loadBanner();

        loadNaturalBeauty();

        Slider.init(new PicassoImageLoadingService());
    }

    private void loadNaturalBeauty() {
        naturalRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<NaturalBeauty> naturalBeauties = new ArrayList<>();

                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot naturalSnapShot : task.getResult()){
                        NaturalBeauty naturalBeauty = naturalSnapShot.toObject(NaturalBeauty.class);
                        naturalBeauties.add(naturalBeauty);
                    }
                    iNaturalBeautyLoadListener.onNaturalBeautyLoadSuccess(naturalBeauties);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iNaturalBeautyLoadListener.onNaturalBeautyLoadFailed(e.getMessage());
            }
        });
    }

    private void loadBanner() {
        bannerRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<Banner> banners = new ArrayList<>();

                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot bannerSnapShot : task.getResult()){
                        Banner banner = bannerSnapShot.toObject(Banner.class);
                        banners.add(banner);
                    }
                    iBannerLoadListener.onBannerLoadSuccess(banners);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iBannerLoadListener.onBannerLoadFailed(e.getMessage());
            }
        });
    }

    @Override
    public void onBannerLoadSuccess(List<Banner> banners) {
        banner_slider.setAdapter(new HomeSliderAdapter(banners));
    }

    @Override
    public void onBannerLoadFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNaturalBeautyLoadSuccess(List<NaturalBeauty> naturalBeauties) {
        recucler_nature.setHasFixedSize(true);
        recucler_nature.setLayoutManager(new LinearLayoutManager(this));
        recucler_nature.setAdapter(new NaturalBeautyAdapter(getApplicationContext(), naturalBeauties));
    }

    @Override
    public void onNaturalBeautyLoadFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
