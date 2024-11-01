package fhnw.emoba.blockbuster.model

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.blockbuster.data.models.MovieVideoItem

class YoutubePlayerModel(val activity: ComponentActivity, movieVideoItem: MovieVideoItem) {
    var youtubeURLKey by mutableStateOf(movieVideoItem.key)

}