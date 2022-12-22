package com.example.ArfanUAS.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val result: List<DataEntitasMovie>
)
