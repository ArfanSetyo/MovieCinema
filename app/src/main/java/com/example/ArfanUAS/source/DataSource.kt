package com.example.ArfanUAS.source

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.ArfanUAS.model.*
import com.example.ArfanUAS.vo.Resource

// kumpulan database yang akan diakses yang akan menampilkan data
interface DataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getAllTV(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getOneMovie(movieID: Int): LiveData<Resource<MovieEntity>>
    fun getOneTV(tvShowID: Int): LiveData<Resource<TvShowEntity>>

    fun setMoviesFav(movie: MovieEntity, state: Boolean)
    fun setTVShowsFav(tvShow: TvShowEntity, state: Boolean)

    fun getMoviesFav(context: Context?): LiveData<PagedList<MovieEntity>>
    fun getTVShowsFav(context: Context?): LiveData<PagedList<TvShowEntity>>
}