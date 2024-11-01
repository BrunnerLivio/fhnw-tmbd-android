package fhnw.emoba.blockbuster.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.blockbuster.data.models.MovieItem

object FavoritesStore {

    var favorites: List<MovieItem> by mutableStateOf(emptyList())

    fun addFavorite(movie: MovieItem) {
        favorites = favorites + movie
    }

    fun removeFavorite(movie: MovieItem) {
        favorites = favorites - movie
    }

    fun toggleFavorite(movie: MovieItem) {
        if (isFavorite(movie.id)) {
            removeFavorite(movie)
        } else {
            addFavorite(movie)
        }
    }


    fun isFavorite(movieId: Int): Boolean {
        return favorites.any { it.id == movieId }
    }
}