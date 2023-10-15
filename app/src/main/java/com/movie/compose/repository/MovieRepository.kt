package com.movie.compose.repository

import com.movie.compose.repository.remote.ApiResult
import com.movie.compose.repository.remote.RetrofitInstance
import com.movie.compose.repository.remote.handleApi
import com.movie.compose.repository.remote.models.MovieResponse

class MovieRepository {
    private val movieService = RetrofitInstance.movieService

    suspend fun getMovies(search: String = "Batman", page: Int = 1): ApiResult<MovieResponse> {
        return handleApi {
            movieService.getMovies(search, page, "b9418cd6")
        }
    }

}