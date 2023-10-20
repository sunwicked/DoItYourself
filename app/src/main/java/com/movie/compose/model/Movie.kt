package com.movie.compose.model

import androidx.compose.runtime.Immutable

@Immutable
data class Movie(
    val title: String,
    val year: String = "",
    val poster: String = "",
    val id: String = "-1"
):ListItem