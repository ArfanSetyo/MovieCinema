package com.example.ArfanUAS.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.ArfanUAS.local.ContentDao
import com.example.ArfanUAS.model.MovieEntity
import com.example.ArfanUAS.model.TvShowEntity

// menyimpan data lokal di database
class LocalDataSource private constructor(private val contentDao: ContentDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(contentDao: ContentDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(contentDao).apply {
                INSTANCE = this
            }
    }

    fun getAllDataMovie(): DataSource.Factory<Int, MovieEntity> =
        contentDao.getMovies()

    fun getAllDataTVShow(): DataSource.Factory<Int, TvShowEntity> =
        contentDao.getTVShows()

    fun getFavMovies(): DataSource.Factory<Int, MovieEntity> = contentDao.getFavMovies()

    fun getFavTVShows(): DataSource.Factory<Int, TvShowEntity> = contentDao.getFavTVShows()

    fun getMovieById(id: Int): LiveData<MovieEntity> = contentDao.getMoviesById(id)

    fun getTVShowById(id: Int): LiveData<TvShowEntity> = contentDao.getTVShowsById(id)

    fun insertMovie(movies: List<MovieEntity>) = contentDao.insertMovie(movies)

    fun insertTVShow(tvShow: List<TvShowEntity>) = contentDao.insertTVShow(tvShow)

    fun updateFavMovie(movies: MovieEntity, newState: Boolean) {
        movies.isFav = newState
        contentDao.updateMovie(movies)
    }

    fun updateFavTVShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFav = newState
        contentDao.updateTVShow(tvShow)
    }

}