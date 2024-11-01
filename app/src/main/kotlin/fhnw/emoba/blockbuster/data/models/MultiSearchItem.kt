package fhnw.emoba.blockbuster.data.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.blockbuster.data.DEFAULT_ICON

abstract class MultiSearchItem {
    fun getImagePath(): String {
        if (this is MovieItem) {
            return posterPath
        }
        if (this is PeopleItem) {
            return profilePath
        }
        throw NotImplementedError()
    }

    fun getDisplayName(): String {
        if (this is MovieItem) {
            return title
        }
        if (this is PeopleItem) {
            return name
        }
        throw NotImplementedError()
    }

    var image by mutableStateOf(DEFAULT_ICON.asImageBitmap())
}