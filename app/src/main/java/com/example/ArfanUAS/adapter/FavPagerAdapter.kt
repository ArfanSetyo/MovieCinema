package com.example.ArfanUAS.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ArfanUAS.fragment.MovieFavFragment
import com.example.ArfanUAS.fragment.TvShowFavFragment

// adaptor film favorit
class FavPagerAdapter(appCompatActivity: AppCompatActivity) :
    FragmentStateAdapter(appCompatActivity) {

    private val listFragment = arrayOf(
        MovieFavFragment(),
        TvShowFavFragment()
    )

    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment = listFragment[position]
}