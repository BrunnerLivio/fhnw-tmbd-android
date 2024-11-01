package fhnw.emoba.blockbuster.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class Screen(val title: String) {
    HOME ("Home"),
    MOVIE_DETAIL("Movie Detail"),
    PEOPLE("People"),
    FAVORITES("Favorites")
}

abstract class ScreenModel(val context: BlockbusterModel) {
    abstract fun init()
    var isLoading by mutableStateOf(false)
}

fun createScreenModel(screen: Screen, ctx: BlockbusterModel): ScreenModel {
    return when (screen) {
        Screen.HOME -> HomeModel(ctx)
        Screen.PEOPLE -> PeopleModel(ctx)
        Screen.MOVIE_DETAIL -> MovieDetailModel(ctx)
        Screen.FAVORITES -> FavoritesModel(ctx)
    }
}

data class ScreenState(
    val screen: Screen,
    val model: ScreenModel
)