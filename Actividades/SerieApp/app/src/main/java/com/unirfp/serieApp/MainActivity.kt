package com.unirfp.serieApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unirfp.serieApp.ui.screens.SerieAppScreen
import com.unirfp.serieApp.ui.viewmodel.SerieViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val serieViewModel: SerieViewModel = viewModel()

            SerieAppScreen(serieViewModel)
        }
    }
}
