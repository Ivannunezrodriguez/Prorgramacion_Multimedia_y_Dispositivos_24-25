package com.unirfp.movieapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MovieItem(movie: Movie) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        // Carga la imagen del póster
        AsyncImage(
            model = movie.poster,
            contentDescription = "Poster de ${movie.title}",
            modifier = Modifier.size(width = 80.dp, height = 120.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = movie.title)
            Text(text = "Rating: ${movie.rating}")
        }
    }
}
