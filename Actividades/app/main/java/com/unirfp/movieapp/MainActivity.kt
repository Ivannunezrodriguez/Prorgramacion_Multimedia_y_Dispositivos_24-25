package com.unirfp.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Obtenemos el ViewModel
            val movieViewModel: MovieViewModel = viewModel()

            // Llamamos al composable principal
            MovieAppScreen(movieViewModel)
        }
    }
}
