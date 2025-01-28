package com.unirfp.serieApp

import retrofit2.Response
import retrofit2.http.GET

interface SeriesApiService {

    @GET("series")
    suspend fun getSeries(): Response<List<Serie>>
}
