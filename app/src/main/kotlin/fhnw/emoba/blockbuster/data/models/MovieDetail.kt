package fhnw.emoba.blockbuster.data.models

import fhnw.emoba.blockbuster.data.map
import org.json.JSONObject

class MovieDetail(val jsonObject: JSONObject) {
    val adult = jsonObject.getBoolean("adult")
    val backdropPath = jsonObject.getString("backdrop_path")
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

    val belongsToCollection = jsonObject.optJSONObject("belongs_to_collection")
    val budget = jsonObject.optInt("budget")
    val genres = jsonObject.getJSONArray("genres").map{ Genre(it as JSONObject) }
    val homepage = jsonObject.optString("homepage")
    val imdbId = jsonObject.optString("imdb_id")
    val originCountry = jsonObject.optJSONArray("origin_country")
    val productionCompanies = jsonObject.optJSONArray("production_companies")
    val productionCountries = jsonObject.optJSONArray("production_countries")
    val revenue = jsonObject.optInt("revenue")
    val runtime = jsonObject.optInt("runtime")
    val spokenLanguages = jsonObject.optJSONArray("spoken_languages")
    val status = jsonObject.optString("status")
    val tagline = jsonObject.optString("tagline")
}
