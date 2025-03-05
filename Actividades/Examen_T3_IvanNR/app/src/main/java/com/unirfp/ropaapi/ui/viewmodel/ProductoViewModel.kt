package com.unirfp.ropaapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.unirfp.ropaapi.data.model.Producto
import com.unirfp.ropaapi.data.network.RetrofitClient

class ProductoViewModel : ViewModel() {
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

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