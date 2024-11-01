package fhnw.emoba.blockbuster.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.PeopleAlt
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppTab(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val navigationFn: (BlockbusterModel) -> Unit
) {
    MOVIE(
        "Movies",
        Icons.Filled.Movie,
        Icons.Outlined.Movie,
        { model -> model.navigateTo(Screen.HOME) }),
    PERSON(
        "People",
        Icons.Filled.People,
        Icons.Outlined.PeopleAlt,
        { model -> model.navigateTo(Screen.PEOPLE) }),
    FAVORITE("Favorites", Icons.Filled.Star, Icons.Outlined.StarOutline, { model -> model.navigateTo(Screen.FAVORITES)}),
}