package com.unirfp.movieapp

import ErrorView
import LoadingView
import MovieListScreen
import androidx.compose.runtime.Composable

@Composable
fun MovieAppScreen(viewModel: MovieViewModel) {
    val state = viewModel.uiState.value
    when (state) {
        is UiState.Loading -> LoadingView()
        is UiState.Success -> MovieListScreen(state.movies)
        is UiState.Error   -> ErrorView(state.message)
    }
}
