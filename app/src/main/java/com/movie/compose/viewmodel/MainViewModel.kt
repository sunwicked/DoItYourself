package com.movie.compose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.compose.model.Movie
import com.movie.compose.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    init {
        fetchMovies()
    }
    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movies = repository.getMovies()
                _movies.value = movies?: emptyList()
                Log.e("TAG", "fetchMovies: $movies", )
            } catch (e: Exception) {
                // Handle error
                Log.e("TAG", "fetchMovies: $e", )
            }
        }
    }

    fun screenData(): List<Movie> {
        return listOf(
            Movie("Android"),
            Movie("Compose"),
            Movie("Coroutine"),
            Movie("Retrofit"),
            Movie("Weather")
        )

    }

}
