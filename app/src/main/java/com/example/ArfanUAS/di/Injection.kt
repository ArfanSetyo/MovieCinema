package com.example.ArfanUAS.di

import android.content.Context
import com.example.ArfanUAS.local.DbConfig
import com.example.ArfanUAS.repository.Repository
import com.example.ArfanUAS.source.LocalDataSource
import com.example.ArfanUAS.source.RemoteDataSource
import com.example.ArfanUAS.util.AppExecutors

// mengatur dasar arsitektur aplikasi
object Injection {
    fun provideRepository(context: Context?): Repository {
        val database = DbConfig.getInstance(context)

        val remoteDataResource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.contentDao())
        val appExecutors = AppExecutors()

        return Repository.getInstance(remoteDataResource, localDataSource, appExecutors)
    }
}