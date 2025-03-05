package com.unirfp.ropaapi.ui.screens

import com.unirfp.ropaapi.ui.viewmodel.RetrofitClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.unirfp.ropaapi.data.model.Producto

class ProductoViewModel : ViewModel() {
    private val _productos = mutableStateOf<List<Producto>>(emptyList())
    val productos: State<List<Producto>> = _productos

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    init {
        fetchProductos()
    }

    fun fetchProductos() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getProductos()
                if (response.isSuccessful) {
                    _productos.value = response.body()?.results ?: emptyList()
                } else {
                    _errorMessage.value = "Error al cargar productos"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}