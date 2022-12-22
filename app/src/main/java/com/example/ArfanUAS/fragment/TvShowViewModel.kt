package com.example.ArfanUAS.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.ArfanUAS.model.TvShowEntity
import com.example.ArfanUAS.repository.Repository
import com.example.ArfanUAS.vo.Resource

// Model tampilan acara TV
class TvShowViewModel(private val repository: Repository) : ViewModel() {
    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = repository.getAllTV()

    fun setFavListTVShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.isFav!!
        repository.setTVShowsFav(tvShowEntity, newState)
    }

}