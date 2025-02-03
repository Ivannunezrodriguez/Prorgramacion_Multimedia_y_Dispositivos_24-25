package com.unirfp.serieApp.data.network

import com.unirfp.serieApp.data.model.Serie
import retrofit2.Response
import retrofit2.http.GET

interface SeriesApiService {

    @GET("series")
    suspend fun getSeries(): Response<List<Serie>>
}
