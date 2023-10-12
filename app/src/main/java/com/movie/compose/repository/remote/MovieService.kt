package com.movie.compose.repository.remote


import com.movie.compose.repository.remote.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/")
    suspend fun getMovies(
        @Query("s") search: String,
        @Query("page") page: Int,
        @Query("apikey") apiKey: String
    ): Response<MovieResponse>

}