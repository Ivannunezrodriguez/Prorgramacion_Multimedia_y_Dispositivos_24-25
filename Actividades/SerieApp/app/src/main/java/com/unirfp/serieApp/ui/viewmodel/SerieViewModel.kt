package com.unirfp.serieApp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unirfp.serieApp.data.model.Serie
import com.unirfp.serieApp.data.network.SeriesApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SerieViewModel : ViewModel() {


    val seriesList: MutableState<List<Serie>> = mutableStateOf(emptyList())
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    val errorMessage: MutableState<String?> = mutableStateOf(null)

    init {
        fetchSeries()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://peticiones.online/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun fetchSeries() {
        isLoading.value = true
        errorMessage.value = null

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val call = getRetrofit()
                    .create(SeriesApiService::class.java)
                    .getSeries()

                if (call.isSuccessful) {
                    val list = call.body() ?: emptyList()
                    seriesList.value = list
                } else {
                    errorMessage.value = "Error al cargar series. Código: ${call.code()}"
                }
            } catch (e: Exception) {

                errorMessage.value = "Falla de red: ${e.localizedMessage}"
            } finally {
                isLoading.value = false
            }
        }
    }
}
