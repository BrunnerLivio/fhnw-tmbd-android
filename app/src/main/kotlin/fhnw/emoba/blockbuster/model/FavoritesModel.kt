package fhnw.emoba.blockbuster.model

import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.blockbuster.store.FavoritesStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class FavoritesModel(context: BlockbusterModel) : ScreenModel(context) {
    private val modelScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun init() {
        loadPoastersAsync()
    }

    private fun loadPoastersAsync() {
        isLoading = true
        modelScope.launch {
            val loadTasks = FavoritesStore.favorites.map { movieItem ->
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
}