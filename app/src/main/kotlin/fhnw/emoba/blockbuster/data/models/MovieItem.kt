package fhnw.emoba.blockbuster.data.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.blockbuster.data.DEFAULT_ICON
import fhnw.emoba.blockbuster.model.Palette
import org.json.JSONObject

class MovieItem(val jsonObject: JSONObject) : MultiSearchItem() {
    val adult = jsonObject.getBoolean("adult")
    val backdropPath = jsonObject.getString("backdrop_path")
    val genreIds = jsonObject.optJSONArray("genre_ids")
    val id = jsonObject.getInt("id")
    val originalLanguage = jsonObject.getString("original_language")
    val originalTitle = jsonObject.getString("original_title")
    val overview = jsonObject.getString("overview")
    val popularity = jsonObject.getDouble("popularity")
    val posterPath = jsonObject.getString("poster_path")
    val releaseDate = jsonObject.getString("release_date")
    val title = jsonObject.getString("title")
    val video = jsonObject.getBoolean("video")
    val voteAverage = jsonObject.getDouble("vote_average")
    val voteCount = jsonObject.getInt("vote_count")


    var posterImage by mutableStateOf(DEFAULT_ICON.asImageBitmap())
    var palette: Palette by mutableStateOf(Palette(Color.White, Color.Black, Color.Gray))

}