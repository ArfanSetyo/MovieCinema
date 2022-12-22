package com.example.ArfanUAS.api

import androidx.viewbinding.BuildConfig
import com.example.ArfanUAS.data.Helper.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
// penghubung antara sebuah aplikasi dan aplikasi lainnya, atau antara klien dan server, untuk memungkinkan integrasi fitur tanpa harus menambahkan data secara manual.
        fun getService(): EndPoint {
            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(EndPoint::class.java)
        }

    }
}