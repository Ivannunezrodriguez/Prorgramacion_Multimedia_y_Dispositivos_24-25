package com.unirfp.movieapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MovieViewModel : ViewModel() {

    // Estado de la interfaz (por defecto en Loading)
    val uiState: MutableState<UiState> = mutableStateOf(UiState.Loading)

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        // Corrutina en hilo de E/S
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val service = RetrofitHelper.getRetrofit().create(MovieApiService::class.java)
                val response = service.getPopularMovies()

                if (response.isSuccessful) {
                    val body = response.body()
                    val movies = body?.movies ?: emptyList()
                    uiState.value = UiState.Success(movies)
                } else {
                    uiState.value = UiState.Error("Error al cargar: ${response.code()}")
                }
            } catch (e: HttpException) {
                uiState.value = UiState.Error("Error de servidor: ${e.message}")
            } catch (e: IOException) {
                uiState.value = UiState.Error("Falla de red: Revisar conexión a Internet.")
            } catch (e: Exception) {
                uiState.value = UiState.Error("Error desconocido: ${e.localizedMessage}")
            }
        }
    }
}
