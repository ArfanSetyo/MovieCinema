package com.example.ArfanUAS.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ArfanUAS.model.MovieEntity
import com.example.ArfanUAS.model.TvShowEntity

// sebuah kelas yang berisi methods yang digunakan untuk mengakses database
@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 4, exportSchema = false)
abstract class DbConfig : RoomDatabase() {

    abstract fun contentDao(): ContentDao

    companion object {
        @Volatile
        private var INSTANCE: DbConfig? = null

        fun getInstance(context: Context?): DbConfig =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context!!.applicationContext,
                    DbConfig::class.java,
                    "catalogue.db"
                ).fallbackToDestructiveMigration().build().apply {
                    INSTANCE = this
                }
            }
    }
}