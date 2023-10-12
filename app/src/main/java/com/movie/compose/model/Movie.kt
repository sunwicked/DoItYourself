package com.movie.compose.model

data class Movie(
    val title: String,
    val year: String = "",
    val poster: String = "",
    val id: String = "-1"
)