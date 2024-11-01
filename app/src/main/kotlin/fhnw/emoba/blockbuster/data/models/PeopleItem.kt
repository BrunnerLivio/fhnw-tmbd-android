package fhnw.emoba.blockbuster.data.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.blockbuster.data.DEFAULT_ICON
import fhnw.emoba.blockbuster.data.map
import org.json.JSONObject

class PeopleItem(val jsonObject: JSONObject): MultiSearchItem() {
    val adult = jsonObject.getBoolean("adult")
    val gender = jsonObject.getInt("gender")
    val id = jsonObject.getInt("id")
    val knownForDepartment = jsonObject.optString("known_for_department")
    val name = jsonObject.getString("name")
    val originalName = jsonObject.getString("original_name")
    val popularity = jsonObject.getDouble("popularity")
    val profilePath = jsonObject.optString("profile_path")
    val knownFor = jsonObject.optJSONArray("known_for")?.map { KnownForItem(it) }

    var profileImage by mutableStateOf(DEFAULT_ICON.asImageBitmap())
}

class KnownForItem(val jsonObject: JSONObject) {
    val backdropPath = jsonObject.optString("backdrop_path")
    val id = jsonObject.getInt("id")
    val name = jsonObject.optString("name")
    val originalName = jsonObject.optString("original_name")
    val overview = jsonObject.getString("overview")
    val posterPath = jsonObject.optString("poster_path")
    val mediaType = jsonObject.getString("media_type")
    val adult = jsonObject.getBoolean("adult")
    val originalLanguage = jsonObject.getString("original_language")
    val genreIds = jsonObject.getJSONArray("genre_ids")
    val popularity = jsonObject.getDouble("popularity")
    val firstAirDate = jsonObject.optString("first_air_date")
    val voteAverage = jsonObject.getDouble("vote_average")
    val voteCount = jsonObject.getInt("vote_count")
    val originCountry = jsonObject.optJSONArray("origin_country")
}
