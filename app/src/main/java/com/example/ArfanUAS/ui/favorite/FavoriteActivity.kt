package com.example.ArfanUAS.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.example.ArfanUAS.R
import com.example.ArfanUAS.adapter.FavPagerAdapter
import com.example.ArfanUAS.databinding.ActivityFavoriteBinding

// Menetapkan tema untuk layar pembuka untuk mengubah tampilannya
class FavoriteActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFavoriteBinding.inflate(layoutInflater) }

    private val tabTitle = intArrayOf(
        R.string.movies,
        R.string.tvshow
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val tab = binding.tabsFav
        val pager = binding.vpFavorite
        pager.adapter = FavPagerAdapter(this)

        TabLayoutMediator(tab, pager) { tb, pos ->
            tb.text = resources.getString(tabTitle[pos])
        }.attach()

        binding.tbFavorite.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(binding.tbFavorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tbFavorite.setNavigationOnClickListener {
            finish()
        }

    }
}