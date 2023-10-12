package com.movie.compose.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movie.compose.model.Movie
import com.movie.compose.ui.theme.MovieComposeTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieScreen(movies: List<Movie>) {
    LazyColumn  {
        items(movies,
            key = { movie ->
                movie.id
            }) { movie ->
            MovieRow(movie, Modifier.animateItemPlacement())
            Spacer(modifier = Modifier.size( 8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    MovieComposeTheme {
        MovieScreen(listOf(Movie("Android"),Movie("Weather")))
    }
}