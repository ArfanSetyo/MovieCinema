package com.example.ArfanUAS.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataEntitasMovie(

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("genre_ids")
    val genre: List<Int>? = null,

    @SerializedName("vote_average")
    val rating: Double? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("poster_path")
    val imgPoster: String? = null,

    @SerializedName("backdrop_path")
    val imgBackground: String? = null,

    @SerializedName("release_date")
    val released: String? = null,

    @SerializedName("first_air_date")
    val firstAir: String? = null,

    @SerializedName("popularity")
    val popularity: String? = null,

    @SerializedName("original_language")
    val language: String? = null,
) : Parcelable