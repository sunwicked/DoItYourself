package com.movie.compose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.compose.model.ListState
import com.movie.compose.repository.MovieRepository
import com.movie.compose.ui.Footer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _movies = MutableStateFlow<List<Any>>(emptyList())
    val movies: StateFlow<List<Any>> = _movies


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

                try {
                    val movies = repository.getMovies("Batman", page)
                    canPaginate = movies?.size == 10
                    val updatedList: MutableList<Any> = _movies.value.toMutableList()
                    if (movies != null) {
                        updatedList.addAll(movies)
                    }

                    updatedList.removeAll {
                        it is Footer
                    }

                    _movies.value = updatedList

                    listState = ListState.IDLE

                    if (canPaginate) page++
                } catch (e: Exception) {
                    // Handle error
                    Log.e("TAG", "fetchMovies: $e")
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
        val footerEnabledList: MutableList<Any> = _movies.value.toMutableList()
        footerEnabledList.add(Footer())
        _movies.value = footerEnabledList.toList()
    }


}
