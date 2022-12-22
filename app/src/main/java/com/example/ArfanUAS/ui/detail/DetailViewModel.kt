package com.example.ArfanUAS.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ArfanUAS.model.MovieEntity
import com.example.ArfanUAS.model.TvShowEntity
import com.example.ArfanUAS.repository.Repository
import com.example.ArfanUAS.vo.Resource

// menyimpan dan mengelola data yang biasanya berhubungan dengan UI
class DetailViewModel(private val detailRepository: Repository) : ViewModel() {
    private var movieId: Int = 0
    private var tvId: Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun setSelectedTV(tvId: Int) {
        this.tvId = tvId
    }

    fun getMovie(): LiveData<Resource<MovieEntity>> = detailRepository.getOneMovie(movieId)

    fun getTV(): LiveData<Resource<TvShowEntity>> = detailRepository.getOneTV((tvId))

    fun setFavMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFav!!
        detailRepository.setMoviesFav(movieEntity, newState)

    }

    fun setFavTVShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.isFav!!
        detailRepository.setTVShowsFav(tvShowEntity, newState)
    }

}