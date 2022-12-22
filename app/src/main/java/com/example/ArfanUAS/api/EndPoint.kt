package com.example.ArfanUAS.api

import com.example.ArfanUAS.data.Helper.API_KEY
import com.example.ArfanUAS.model.DetailMovie
import com.example.ArfanUAS.model.DetailTvShow
import com.example.ArfanUAS.model.MovieResponse
import com.example.ArfanUAS.model.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// tampilan antar muka untuk aplikasi
interface EndPoint {

    @GET("movie/popular?api_key=$API_KEY")
    fun getListMovies(
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getDetailMovies(
        @Path("movie_id") id: Int
    ): Call<DetailMovie>

    @GET("tv/popular?api_key=$API_KEY")
    fun getListTVShows(
        @Query("page") page: Int
    ): Call<TvResponse>

    @GET("tv/{tv_id}?api_key=$API_KEY")
    fun getDetailTVShows(
        @Path("tv_id") id: Int
    ): Call<DetailTvShow>
}