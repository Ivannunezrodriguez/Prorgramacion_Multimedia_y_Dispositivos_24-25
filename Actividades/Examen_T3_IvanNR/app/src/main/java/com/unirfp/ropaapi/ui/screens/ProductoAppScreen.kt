package com.unirfp.ropaapi.ui.screens

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unirfp.ropaapi.data.model.Producto
import com.unirfp.ropaapi.ui.viewmodel.ProductoViewModel

@Composable
fun ProductoAppScreen(viewModel: ProductoViewModel) {
    val productos = viewModel.productos.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.errorMessage.collectAsState().value

    var selectedProducto by remember { mutableStateOf<Producto?>(null) }

    when {
        error != null -> ErrorView(error, onRetry = { viewModel.fetchProductos() })
        isLoading -> LoadingView()
        selectedProducto == null -> ProductosListScreen(productos) { selectedProducto = it }
        else -> ProductoDetailScreen(producto = selectedProducto!!, onBack = { selectedProducto = null })
    }
}

/* LoadingView y ErrorView */

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = message)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetry) { Text("Reintentar") }
        }
    }
}
