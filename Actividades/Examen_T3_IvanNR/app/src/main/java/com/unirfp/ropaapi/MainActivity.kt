package com.unirfp.ropaapi
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.unirfp.ropaapi.ui.screens.ProductoAppScreen
import com.unirfp.ropaapi.ui.viewmodel.ProductoViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val productoViewModel: ProductoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductoAppScreen(productoViewModel)
        }
        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launch {
            productoViewModel.fetchProductos()
        }
    }
}