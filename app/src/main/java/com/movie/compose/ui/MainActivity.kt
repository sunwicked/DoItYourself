package com.movie.compose.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.movie.compose.ui.screen.MovieScreen
import com.movie.compose.ui.theme.MovieComposeTheme
import com.movie.compose.viewmodel.MainViewModel

class MainActivity : FragmentActivity() {
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MovieComposeTheme {

                val movies by viewModel.movies.collectAsState()

                LaunchedEffect(Unit) {
                    viewModel.fetchMovies()
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val items = remember {
                        viewModel.screenData()
                    }
                    MovieScreen(items)
                }
            }
        }
    }
}


