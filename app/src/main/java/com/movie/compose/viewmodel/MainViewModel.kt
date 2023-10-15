package com.movie.compose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.compose.model.ListState
import com.movie.compose.model.Movie
import com.movie.compose.repository.MovieRepository
import com.movie.compose.repository.remote.ApiResult
import com.movie.compose.repository.remote.models.SearchItem
import com.movie.compose.model.Footer
import com.movie.compose.model.ListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _movies = MutableStateFlow<List<ListItem>>(emptyList())
    val movies: StateFlow<List<ListItem>> = _movies


    private var page = 1
    private var canPaginate = false
    private var listState = ListState.IDLE

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            if (shouldFetchMovies()) {
                listState = if (page == 1) ListState.LOADING else ListState.PAGINATING


                when (val response = repository.getMovies("Batman", page)) {
                    is ApiResult.Success -> {
                        val movies = response.data.search.convert()
                        canPaginate = movies.size == 10
                        val updatedList: MutableList<ListItem> = _movies.value.toMutableList()
                        updatedList.addAll(movies)

                        updatedList.removeAll {
                            it is Footer
                        }

                        _movies.value = updatedList

                        listState = ListState.IDLE

                        if (canPaginate) page++
                    }

                    is ApiResult.Error -> {
                        // show Error Snackbar
                        Log.e("TAG", "fetchMovies: ${response.code} and ${response.message}")
                    }

                    is ApiResult.Exception -> {
                        Log.e("TAG", "fetchMovies: ${response.e}")
                    }
                }


            } else {
                listState = if (page == 1) ListState.ERROR else ListState.PAGINATION_EXHAUST
            }

        }
    }

    private fun shouldFetchMovies() =
        page == 1 || (page != 1 && canPaginate) && listState == ListState.IDLE

    fun updatePage() {

        if (listState != ListState.PAGINATING && listState != ListState.LOADING) {
            addFooter()
        }

        if (listState != ListState.PAGINATING) {
            fetchMovies()
        }

    }

    private fun addFooter() {
        val footerEnabledList: MutableList<ListItem> = _movies.value.toMutableList()
        footerEnabledList.add(Footer())
        _movies.value = footerEnabledList.toList()
    }


}

private fun List<SearchItem?>?.convert(): List<Movie> {
    return this?.map {
        Movie(
            it?.title ?: "",
            it?.year ?: "",
            it?.poster ?: "",
            it?.imdbID ?: ""
        )
    } ?: emptyList()
}