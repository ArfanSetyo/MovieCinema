package com.example.ArfanUAS.adapter

import com.example.ArfanUAS.model.TvShowEntity

// panggilan balik tv konten
interface ContentTvCallback {
    fun onItemClicked(dataTv: TvShowEntity)
}