package com.example.shakil.kotlinbannerslider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shakil.kotlinbannerslider.Adapter.HomeSliderAdapter
import com.example.shakil.kotlinbannerslider.Adapter.NaturalBeautyAdapter
import com.example.shakil.kotlinbannerslider.Interface.IBannerLoadListener
import com.example.shakil.kotlinbannerslider.Interface.INaturalBeautyLoadListener
import com.example.shakil.kotlinbannerslider.Model.Banner
import com.example.shakil.kotlinbannerslider.Model.NaturalBeauty
import com.example.shakil.kotlinbannerslider.Service.PicassoImageLoadingService
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import ss.com.bannerslider.Slider

class MainActivity : AppCompatActivity(), IBannerLoadListener, INaturalBeautyLoadListener {

    //FireStore
    lateinit var bannerRef: CollectionReference
    lateinit var naturalRef: CollectionReference

    //Interface
    lateinit var iBannerLoadListener: IBannerLoadListener
    lateinit var iNaturalBeautyLoadListener: INaturalBeautyLoadListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init
        Slider.init(PicassoImageLoadingService())

        iBannerLoadListener = this
        iNaturalBeautyLoadListener = this

        bannerRef = FirebaseFirestore.getInstance().collection("Banner")
        naturalRef = FirebaseFirestore.getInstance().collection("NaturalBeauty")

        loadBanner()

        loadNaturalBeauty()
    }

    private fun loadNaturalBeauty() {
        naturalRef.get().addOnCompleteListener{task ->
            val naturalBeauties = ArrayList<NaturalBeauty>()

            if (task.isSuccessful){
                for (naturalSnapShot in task.result!!){
                    val naturalBeauty = naturalSnapShot.toObject<NaturalBeauty>(NaturalBeauty::class.java!!)
                    naturalBeauties.add(naturalBeauty)
                }
                iNaturalBeautyLoadListener.onNaturalBeautyLoadSuccess(naturalBeauties)
            }
        }.addOnFailureListener { e -> iNaturalBeautyLoadListener.onNaturalBeautyLoadFailed("" + e.message) }
    }

    private fun loadBanner() {
        bannerRef.get().addOnCompleteListener{task ->
            val banners = ArrayList<Banner>()

            if (task.isSuccessful){
                for (bannerSnapShot in task.result!!){
                    val banner = bannerSnapShot.toObject<Banner>(Banner::class.java!!)
                    banners.add(banner)
                }
                iBannerLoadListener.onBannerLoadSuccess(banners)
            }
        }.addOnFailureListener { e -> iBannerLoadListener.onBannerLoadFailed("" + e.message) }
    }

    override fun onBannerLoadSuccess(banners: List<Banner>) {
        banner_slider.setAdapter(HomeSliderAdapter(banners))
    }

    override fun onBannerLoadFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onNaturalBeautyLoadSuccess(naturalBeauties: List<NaturalBeauty>) {
        recycler_nature.setHasFixedSize(true)
        recycler_nature.layoutManager = LinearLayoutManager(this)
        recycler_nature.adapter = NaturalBeautyAdapter(applicationContext, naturalBeauties)
    }

    override fun onNaturalBeautyLoadFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
