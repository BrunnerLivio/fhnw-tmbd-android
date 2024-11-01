package fhnw.emoba.blockbuster.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.blockbuster.data.models.MovieItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class HomeModel(context: BlockbusterModel) : ScreenModel(context) {
    private val modelScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)
    var movieItems: List<MovieItem> by mutableStateOf(emptyList())


    override fun init() {
        getMoviesAsync();
    }

    fun getMoviesAsync() {
        isLoading = true
        movieItems = emptyList()
        modelScope.launch {
            movieItems = context.tmbdService.discoverMovies().results

            val loadTasks = movieItems.map { movieItem ->
                modelScope.async {
                    movieItem.posterImage = ImageLoader.loadImage(movieItem.posterPath) { path ->
                        context.tmbdService.getPoster(path).asImageBitmap()
                    }.await()
                    movieItem.palette = getPalette(movieItem.posterImage)
                }
            }

            // This is usually not a good idea, instead each movieItem should load itself
            // and show an e.g. Skeleton until everything is loaded
            // That way the UI can be updated incrementally
            loadTasks.awaitAll()

            isLoading = false
        }
    }


    fun gotoMovieDetail(movieItem: MovieItem) {
        context.selectedMovieItem = movieItem;
        context.navigateTo(Screen.MOVIE_DETAIL)
    }

}