package com.unirfp.ropaapi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.compose.rememberAsyncImagePainter
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.unirfp.ropaapi.data.model.Producto
import androidx.compose.ui.platform.LocalContext

@Composable
fun ProductosListScreen(productos: List<Producto>, onItemClick: (Producto) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.fillMaxSize().background(Color(0xFF121212))
            .padding(8.dp, 30.dp)
    ) {
        items(productos) { producto ->
            ProductoGridItem(producto = producto, onClick = { onItemClick(producto) })
        }
    }
}

@Composable
fun ProductoGridItem(producto: Producto, onClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(4.dp).clickable { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = producto.image,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(2 / 3f)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Text(
            text = producto.name,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
