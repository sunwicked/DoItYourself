package com.movie.compose.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movie.compose.ui.screen.MovieScreen
import com.movie.compose.ui.theme.MovieComposeTheme
import com.movie.compose.viewmodel.MainViewModel

class MainActivity : FragmentActivity() {
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val movies by viewModel.movies.collectAsStateWithLifecycle()

            MovieComposeTheme {
           // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieScreen(movies) { viewModel::updatePage } // https://medium.com/icerock/optimize-or-die-profiling-and-optimization-in-jetpack-compose-a165c8897b3f
                }
            }
        }
    }
}


