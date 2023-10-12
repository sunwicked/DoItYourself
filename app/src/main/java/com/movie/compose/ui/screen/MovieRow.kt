package com.movie.compose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movie.compose.model.Movie
import com.movie.compose.ui.theme.MovieComposeTheme

@Composable
fun MovieRow(item: Movie, modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .height(64.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center) {
        Text(
            text = item.title,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp),
            style =  MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieRowPreview() {
    MovieComposeTheme {
        MovieRow(Movie("Android"))
    }
}