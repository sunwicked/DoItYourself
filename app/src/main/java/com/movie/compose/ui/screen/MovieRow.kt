package com.movie.compose.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.movie.compose.R
import com.movie.compose.model.Movie
import com.movie.compose.ui.theme.MovieComposeTheme

@Composable
fun MovieRow(item: Movie, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier.padding(8.dp).height(160.dp)
    ) {
        Row(
            modifier = modifier
                       .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {

            AsyncImage(
                model = item.poster,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "The delasign logo",
                modifier = Modifier.fillMaxWidth(fraction = 0.3f)
            )

            Column {
                Text(
                    text = item.title,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 16.dp,top = 32.dp),
                    style = MaterialTheme.typography.headlineLarge,
                    maxLines = 1
                )
                Text(
                    text = item.year,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieRowPreview() {
    MovieComposeTheme {
        MovieRow(Movie("Android", "2050", "https://delasign.com/delasignBlack.png"))
    }
}