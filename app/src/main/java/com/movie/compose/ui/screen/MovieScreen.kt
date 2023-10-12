package com.movie.compose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.movie.compose.model.Movie
import com.movie.compose.ui.theme.MovieComposeTheme

@Composable
fun MovieScreen(items: List<Movie>, modifier: Modifier = Modifier) {
    Column {
        items.forEach { weather ->
            MovieRow(weather)
            Divider(color = Color.Black)
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