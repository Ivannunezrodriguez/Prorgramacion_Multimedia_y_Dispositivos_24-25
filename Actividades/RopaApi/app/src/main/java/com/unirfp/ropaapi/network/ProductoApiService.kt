package com.unirfp.ropaapi.network

import com.unirfp.ropaapi.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductoApiService {
    @GET("products")
    suspend fun getProducts(): Response<ApiResponse>
}
