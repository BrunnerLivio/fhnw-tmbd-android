package fhnw.emoba.blockbuster.model

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.blockbuster.data.models.MovieItem
import fhnw.emoba.blockbuster.data.TMDBService


open class BlockbusterModel(val activity: ComponentActivity, val tmbdService: TMDBService) {
    var selectedMovieItem: MovieItem? by mutableStateOf(null)
    var darkTheme by mutableStateOf(false)
    var showTmdbBar by mutableStateOf(true)
    var selectedTab by mutableStateOf(AppTab.MOVIE)
    var search = SearchModel(this)

    var screenState by mutableStateOf(
        ScreenState(
            Screen.HOME,
            createScreenModel(Screen.HOME, this)
        )
    )
        private set

    private val screenModels = mutableMapOf<Screen, ScreenModel>()

    fun navigateTo(screen: Screen) {
        val model =
            screenModels[screen] ?: createScreenModel(screen, this).also {
                screenModels[screen] = it
            }
        screenState = ScreenState(screen, model)
        screenState.model.init()
    }

    fun gotoMovieDetail(movieItem: MovieItem) {
        selectedMovieItem = movieItem;
        navigateTo(Screen.MOVIE_DETAIL)
    }

    fun init() {
        screenState.model.init()
        search.init()
    }
}