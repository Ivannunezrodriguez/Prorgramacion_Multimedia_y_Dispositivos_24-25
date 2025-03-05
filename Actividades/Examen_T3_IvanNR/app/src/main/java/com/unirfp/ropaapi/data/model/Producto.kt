package com.unirfp.ropaapi.data.model

import com.google.gson.annotations.SerializedName

data class Producto(

    val _id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val image: String,
    val active: Boolean
)
