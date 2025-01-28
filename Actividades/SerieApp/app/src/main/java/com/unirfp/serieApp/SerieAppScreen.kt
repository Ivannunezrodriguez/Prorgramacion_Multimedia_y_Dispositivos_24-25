package com.unirfp.serieApp

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SerieAppScreen(viewModel: SerieViewModel) {
    val seriesList = viewModel.seriesList.value
    val isLoading = viewModel.isLoading.value
    val error = viewModel.errorMessage.value


    var selectedSerie by remember { mutableStateOf<Serie?>(null) }

    when {
        error != null -> {
            ErrorView(error)
        }
        isLoading -> {
            LoadingView()
        }
        else -> {
            if (selectedSerie == null) {

                SeriesListScreen(
                    series = seriesList,
                    onItemClick = { serie ->
                        selectedSerie = serie
                    }
                )
            } else {

                SeriesDetailScreen(
                    serie = selectedSerie!!,
                    onBack = {
                        selectedSerie = null
                    }
                )
            }
        }
    }
}

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Error: $message")
    }
}
