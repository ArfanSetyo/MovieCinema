package com.example.ArfanUAS.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ArfanUAS.api.ApiService
import com.example.ArfanUAS.model.*
import com.example.ArfanUAS.util.EssIdlingResources
import retrofit2.Call
import retrofit2.Response

// Menyimpan dan mengambil data dari API
class RemoteDataSource {
    private val idling = EssIdlingResources

    companion object {
        const val TAG = "Remote Data Source"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            RemoteDataSource().apply { instance = this }
        }
    }

    fun getMovies(): LiveData<ApiResponse<List<DataEntitasMovie>>> {
        idling.increment()
        val result = MutableLiveData<ApiResponse<List<DataEntitasMovie>>>()
        ApiService.getService().getListMovies(1)
            .enqueue(object : retrofit2.Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    val body = response.body()?.result
                    val movieResults = ArrayList<DataEntitasMovie>()
                    if (body != null) {
                        for (movie in body) {
                            val movieResponse = DataEntitasMovie(
                                id = movie.id,
                                title = movie.title,
                                imgPoster = movie.imgPoster,
                                rating = movie.rating,
                                language = movie.language,
                                released = movie.released,
                                genre = movie.genre,
                                imgBackground = movie.imgBackground,
                                firstAir = movie.firstAir,
                                overview = movie.overview,
                                popularity = movie.popularity
                            )
                            movieResults.add(movieResponse)
                        }
                    }

                    result.value = ApiResponse.success(movieResults)
                    idling.decrement()

                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e(TAG, "Failure to Get List Movie ${t.message}")
                    idling.decrement()
                }
            })
        return result
    }

    fun getTvShows(): LiveData<ApiResponse<List<DataEntitasTv>>> {
        idling.increment()
        val result = MutableLiveData<ApiResponse<List<DataEntitasTv>>>()
        ApiService.getService().getListTVShows(1).enqueue(object : retrofit2.Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                val body = response.body()?.result
                val tvResults = ArrayList<DataEntitasTv>()
                if (body != null) {
                    for (tv in body) {
                        val tvResponse = DataEntitasTv(
                            id = tv.id,
                            title = tv.title,
                            imgPoster = tv.imgPoster,
                            rating = tv.rating,
                            language = tv.language,
                            released = tv.released,
                            genre = tv.genre,
                            imgBackground = tv.imgBackground,
                            overview = tv.overview,
                            popularity = tv.popularity
                        )
                        tvResults.add(tvResponse)
                    }

                }

                result.value = ApiResponse.success(tvResults)
                idling.decrement()

            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e(TAG, "Failure to Get List TV ${t.message}")
                idling.decrement()
            }
        })
        return result
    }

    fun getDetailMovies(id: Int?): LiveData<ApiResponse<DetailMovie>> {
        idling.increment()
        val result = MutableLiveData<ApiResponse<DetailMovie>>()
        ApiService.getService().getDetailMovies(id!!)
            .enqueue(object : retrofit2.Callback<DetailMovie> {
                override fun onResponse(call: Call<DetailMovie>, response: Response<DetailMovie>) {
                    val body = response.body()
                    val genre = response.body()?.genre

                    val detMovieResponse = DetailMovie(
                        id = body?.id!!,
                        title = body.title,
                        imgPoster = body.imgPoster,
                        rating = body.rating,
                        language = body.language,
                        released = body.released,
                        genre = genre,
                        imgBackground = body.imgBackground,
                        firstAir = body.firstAir,
                        overview = body.overview,
                        popularity = body.popularity,
                        revenue = body.revenue,
                        runtime = body.runtime
                    )

                    result.value = ApiResponse.success(detMovieResponse)
                    idling.decrement()

                }

                override fun onFailure(call: Call<DetailMovie>, t: Throwable) {
                    Log.e(TAG, "Failure to Get Detail ${t.message}")
                    idling.decrement()
                }

            })
        return result
    }

    fun getDetailTvShow(id: Int?): LiveData<ApiResponse<DetailTvShow>> {
        idling.increment()
        val result = MutableLiveData<ApiResponse<DetailTvShow>>()
        ApiService.getService().getDetailTVShows(id!!)
            .enqueue(object : retrofit2.Callback<DetailTvShow> {
                override fun onResponse(
                    call: Call<DetailTvShow>,
                    response: Response<DetailTvShow>
                ) {
                    val body = response.body()
                    val genre = response.body()?.genre

                    val detTvResponse = DetailTvShow(
                        id = body?.id!!,
                        title = body.title,
                        imgPoster = body.imgPoster,
                        rating = body.rating,
                        language = body.language,
                        released = body.released,
                        genre = genre,
                        imgBackground = body.imgBackground,
                        firstAir = body.firstAir,
                        overview = body.overview,
                        popularity = body.popularity,
                        revenue = body.revenue,
                        runtime = body.runtime
                    )

                    result.value = ApiResponse.success(detTvResponse)
                    idling.decrement()

                }

                override fun onFailure(call: Call<DetailTvShow>, t: Throwable) {
                    Log.e(TAG, "Failure to Get Detail Tv ${t.message}")
                    idling.decrement()
                }
            })
        return result
    }

}