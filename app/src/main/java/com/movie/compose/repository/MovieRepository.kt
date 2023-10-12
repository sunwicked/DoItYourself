package com.movie.compose.repository

import com.movie.compose.model.Movie
import com.movie.compose.repository.remote.RetrofitInstance
import com.movie.compose.repository.remote.models.SearchItem

class MovieRepository {
    private val movieService = RetrofitInstance.movieService

    suspend fun getMovies(search: String ="Batman", page: Int=1): List<Movie>? {
        val movieDTOs = movieService.getMovies(search, page, "b9418cd6").search
        return movieDTOs?.convert()
    }
}

private fun List<SearchItem?>?.convert(): List<Movie> {
    return this?.map {
        Movie(it?.title?:"")
    }?: emptyList()
}
