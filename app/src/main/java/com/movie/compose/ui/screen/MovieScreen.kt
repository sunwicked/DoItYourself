package com.movie.compose.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movie.compose.model.Movie
import com.movie.compose.model.Footer
import com.movie.compose.model.ListItem
import com.movie.compose.ui.theme.MovieComposeTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieScreen(movies: List<ListItem>, onLast:()->Unit ) {



    val scrollState = rememberLazyListState()

    // observer when reached end of list
    val endOfListReached by remember {
        derivedStateOf {
            !scrollState.canScrollForward
        }
    }

    // act when end of list reached
    LaunchedEffect(endOfListReached) {

        onLast.invoke()

    }

    LazyColumn(state = scrollState) {
        items(items = movies) { item ->
            when (item) {
                is Movie -> {
                    MovieRow(item, Modifier.animateItemPlacement())
                    Spacer(modifier = Modifier.size(8.dp))
                }

                is Footer -> {

                        FooterRow()

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    MovieComposeTheme {
        MovieScreen(listOf(Movie("Android"), Movie("Weather"))) {}
    }
}