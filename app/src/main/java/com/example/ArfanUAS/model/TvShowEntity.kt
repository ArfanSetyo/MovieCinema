package com.example.ArfanUAS.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "rating")
    val rating: Double?,

    @ColumnInfo(name = "genre")
    var genre: String?,

    @ColumnInfo(name = "desc")
    val overview: String?,

    @ColumnInfo(name = "poster_path")
    val imgPoster: String?,

    @ColumnInfo(name = "backdrop_path")
    val imgBackground: String?,

    @ColumnInfo(name = "release")
    val released: String?,

    @ColumnInfo(name = "first_air")
    val firstAir: String?,

    @ColumnInfo(name = "popularity")
    val popularity: String?,

    @ColumnInfo(name = "language")
    val language: String?,

    @ColumnInfo(name = "is_fav")
    var isFav: Boolean?,

    )