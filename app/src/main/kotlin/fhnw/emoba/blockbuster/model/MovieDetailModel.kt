package fhnw.emoba.blockbuster.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.blockbuster.data.DEFAULT_ICON
import fhnw.emoba.blockbuster.data.models.MovieDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MovieDetailModel(context: BlockbusterModel) : ScreenModel(context) {
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    var movie by mutableStateOf<MovieDetail?>(null)
    var videos by mutableStateOf(emptyList<YoutubePlayerModel>())
    var palette: Palette by mutableStateOf(Palette(Color.Black, Color.White, Color.Gray))
    var posterImage: ImageBitmap by mutableStateOf(DEFAULT_ICON.asImageBitmap())

    override fun init() {
        isLoading = true

        modelScope.launch {
            movie = getMovieDetailAsync().await()

            val videosDeferred = modelScope.async {
                videos = getMovieVideosAsync(movie!!).await()
            }
            val imageDeferred = modelScope.async {
                posterImage = ImageLoader.loadImage(
                    movie!!.posterPath,
                ) { path -> context.tmbdService.getPoster(path).asImageBitmap() }.await()

                palette = getPalette(posterImage)

            }

            videosDeferred.await()
            imageDeferred.await()

            isLoading = false
        }

    }

    private fun getMovieDetailAsync(): Deferred<MovieDetail> {
        return modelScope.async {
            context.tmbdService.movieDetail(context.selectedMovieItem!!.id)
        }
    }

    fun getMovieVideosAsync(movie: MovieDetail): Deferred<List<YoutubePlayerModel>> {
        return modelScope.async {
            context.tmbdService.movieVideos(movie.id).results
                .filter { it.site == "YouTube" }
                .map {
                    YoutubePlayerModel(context.activity, it)
                }
                .take(3)
        }
    }
}