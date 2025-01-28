package com.unirfp.serieApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun SeriesDetailScreen(
    serie: Serie,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF020238))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        AsyncImage(
            model = serie.image,
            contentDescription = "Poster de ${serie.title}",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))


        Text(
            text = serie.title,
            fontSize = 24.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(25.dp))

        Row {
            Text(
                text = "Creador: ",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${serie.creator}",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = "Rating: ",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${serie.rating}",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = "Fecha: ",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${serie.dates}",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = "Canal: ",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${serie.channel}",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = onBack) {
            Text("Volver")
        }
    }
}
