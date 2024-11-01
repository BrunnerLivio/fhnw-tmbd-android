package fhnw.emoba.blockbuster.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fhnw.emoba.blockbuster.data.models.MovieItem
import fhnw.emoba.blockbuster.store.FavoritesStore

@Composable
fun FavoriteIcon(movieItem: MovieItem, favoriteColor: Color, color: Color) {
    val isFavorite = FavoritesStore.isFavorite(movieItem.id)
    Log.d(movieItem.title, "isFavorite: $isFavorite")
    Icon(
        imageVector = if (isFavorite) Icons.Filled.Star else Icons.Filled.StarOutline,
        contentDescription = "Favorite Icon",
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = { FavoritesStore.toggleFavorite(movieItem) }),
        tint = if (isFavorite) favoriteColor else color
    )
}