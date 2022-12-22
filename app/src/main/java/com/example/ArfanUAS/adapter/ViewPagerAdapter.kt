package com.example.ArfanUAS.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ArfanUAS.fragment.MoviesFragment
import com.example.ArfanUAS.fragment.TvShowFragment

// lihat adaptor halaman
class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {


    private val listFragment = arrayOf(
        MoviesFragment(),
        TvShowFragment()
    )

    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment = listFragment[position]


}