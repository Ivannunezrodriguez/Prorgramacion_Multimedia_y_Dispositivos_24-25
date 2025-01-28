package com.unirfp.serieApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SeriesListScreen(
    series: List<Serie>,
    onItemClick: (Serie) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF010132))
            .padding(top = 40.dp)
    ) {
        items(series) { s ->
            SeriesItem(
                serie = s,
                onClick = { onItemClick(s) }
            )
        }
    }
}
