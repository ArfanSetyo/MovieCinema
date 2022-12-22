package com.example.ArfanUAS.source

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ArfanUAS.di.Injection
import com.example.ArfanUAS.fragment.FavoriteViewModel
import com.example.ArfanUAS.fragment.MoviesViewModel
import com.example.ArfanUAS.fragment.TvShowViewModel
import com.example.ArfanUAS.repository.Repository
import com.example.ArfanUAS.ui.detail.DetailViewModel

// mengenkapsulasi data bagi pengontrol UI agar data dapat bertahan saat terjadi perubahan konfigurasi
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(repository) as T
            }
            else -> throw Throwable("invalid " + modelClass.name)
        }
    }
}