package com.example.ArfanUAS.model

import com.google.gson.annotations.SerializedName

data class DetailTvShow(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val title: String? = null,

    @SerializedName("genres")
    val genre: List<Genre>? = null,

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

    @SerializedName("revenue")
    val revenue: String? = null,

    @SerializedName("runtime")
    val runtime: String? = null,
)
