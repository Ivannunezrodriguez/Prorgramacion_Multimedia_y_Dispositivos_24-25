package com.unirfp.ropaapi.data.network

import com.unirfp.ropaapi.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface ProductoApiService {
    @GET("products")
    suspend fun getProductos(): Response<ApiResponse>
}