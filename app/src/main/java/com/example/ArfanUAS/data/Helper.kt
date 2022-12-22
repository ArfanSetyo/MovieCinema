package com.example.ArfanUAS.data

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

// merupakan kumpulan API untuk mengelola database
object Helper {
    const val TYPE_MOVIE = "TYPE_MOVIE"
    const val TYPE_TVSHOW = "TYPE_TVSHOW"
    const val API_KEY = "36f1729a1840474fd1a6c371df59f2e9"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMG_URL = "https://image.tmdb.org/t/p/w500"

    fun setGlideImage(context: Context, imagePath: String?, imageView: ImageView) {
        Glide.with(context).clear(imageView)
        Glide.with(context).load(IMG_URL + imagePath).into(imageView)
    }
}