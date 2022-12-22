package com.example.ArfanUAS.adapter

import com.example.ArfanUAS.model.MovieEntity

// menampilkan data konten movie
interface ContentMovieCallback {
    fun onItemClicked(dataMovie: MovieEntity)
}