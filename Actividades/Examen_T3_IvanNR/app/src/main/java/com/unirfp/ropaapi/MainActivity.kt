package com.unirfp.ropaapi

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unirfp.ropaapi.data.model.ApiResponse
import com.unirfp.ropaapi.ui.screens.ProductoAppScreen
import com.unirfp.ropaapi.ui.screens.ProductoViewModel
import com.unirfp.ropaapi.ui.viewmodel.RetrofitClient
import com.unirfp.serieapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val productoViewModel : ProductoViewModel = viewModel()
            ProductoAppScreen(productoViewModel)
        }

        fetchData()
    }

    private fun fetchData() {
        RetrofitClient.instance.getProductos().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        for (producto in it.results) {
                            Log.d("Producto", "Nombre: ${producto.name}, Precio: ${producto.price}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("Error", "Fallo en la conexión: ${t.message}")
            }
        })
    }
}
