package fhnw.emoba.blockbuster.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import fhnw.emoba.blockbuster.model.YoutubePlayerModel

@Composable
fun YoutubePlayer(model: YoutubePlayerModel) {
    val activityLifecycle = LocalLifecycleOwner.current.lifecycle

    AndroidView(
        factory = {
            YouTubePlayerView(model.activity).apply {
                activityLifecycle.addObserver(this)
                enableAutomaticInitialization = false
                initialize(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(model.youtubeURLKey, 0f)
                    }
                })
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        update = {}
    )

    // Use DisposableEffect for cleanup
    DisposableEffect(Unit) {
        onDispose {
            val youtubePlayerView = YouTubePlayerView(model.activity)
            activityLifecycle.removeObserver(youtubePlayerView)
            youtubePlayerView.release()
        }
    }
}