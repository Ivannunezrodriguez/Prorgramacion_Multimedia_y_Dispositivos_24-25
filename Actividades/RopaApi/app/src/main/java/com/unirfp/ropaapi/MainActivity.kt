package com.unirfp.ropaapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.unirfp.ropaapi.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.instance.getProducts()
                if (response.isSuccessful) {
                    val products = response.body()?.results ?: emptyList()
                    withContext(Dispatchers.Main) {
                        products.forEach { product ->
                            Log.d("API_DATA", "Producto: ${product.name}, Precio: ${product.price}")
                        }
                    }
                } else {
                    Log.e("API_ERROR", "Error en la respuesta: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error en la solicitud: ${e.message}")
            }
        }
    }
}
