package com.unirfp.serieApp.data.model

import com.google.gson.annotations.SerializedName


data class Serie(
    @SerializedName("_id")
    val idMongo: String,
    val id: Int,
    val title: String,
    val creator: String,
    val rating: Double,
    val dates: String,
    val image: String,
    val channel: String
)
