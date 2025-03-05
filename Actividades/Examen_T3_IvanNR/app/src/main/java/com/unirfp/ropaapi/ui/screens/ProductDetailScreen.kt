package com.unirfp.ropaapi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.unirfp.ropaapi.data.model.Producto
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoDetailScreen(producto: Producto, onBack: () -> Unit) {
    Scaffold(
        containerColor = Color(0xFF121212),
        topBar = {
            TopAppBar(
                title = { Text(producto.name, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF1F1F1F)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model =producto.image, contentDescription = null,
                modifier =Modifier
                    .fillMaxWidth()
                    .aspectRatio(16/9f)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                InfoRow(icon=Icons.Default.ArrowOutward, title = "Descripcion", value=producto.description)
                InfoRow(icon=Icons.Default.ArrowOutward, title = "Precio", value=producto.price.toString())
                InfoRow(icon=Icons.Default.ArrowOutward, title = "Categoria", value=producto.category)


            }
        }
    }
}
@Composable
fun InfoRow(icon: ImageVector, title: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$title: ",
            color = Color(0xFFFFFFFF),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            color = Color(0xFFFFFFFF)
        )
    }
}