package com.example.ArfanUAS.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.tabs.TabLayoutMediator
import com.example.ArfanUAS.R
import com.example.ArfanUAS.adapter.ViewPagerAdapter
import com.example.ArfanUAS.databinding.ActivityMainBinding
import com.example.ArfanUAS.ui.favorite.FavoriteActivity

// menampilkan user interface (UI) dari aplikasi yang akan dibuat
class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    private val tabTitle = intArrayOf(
        R.string.movies,
        R.string.tvshow
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.latar_gotham, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.latar_cold_persuit, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.latar_t34, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.latar_a_star, ScaleTypes.CENTER_CROP))

        val imageSlider = activityMainBinding.carouselView
        imageSlider.setImageList(imageList)

        setSupportActionBar(activityMainBinding.toolBar)
        supportActionBar?.elevation = 0f

        val tab = activityMainBinding.tabs
        val vPager = activityMainBinding.viewPager
        vPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tab, vPager) { tb, position ->
            tb.text = resources.getString(tabTitle[position])
        }.attach()

        activityMainBinding.fabFav.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }
    }

}