package com.example.ArfanUAS.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.ArfanUAS.model.MovieEntity
import com.example.ArfanUAS.repository.Repository
import com.example.ArfanUAS.vo.Resource

// model tampilan film
class MoviesViewModel(private val repository: Repository) : ViewModel() {

    fun getMoviesData(): LiveData<Resource<PagedList<MovieEntity>>> = repository.getAllMovies()

    fun setFavListMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFav!!
        repository.setMoviesFav(movieEntity, newState)
    }
}