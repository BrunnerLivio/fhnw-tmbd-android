package fhnw.emoba.blockbuster.data

import android.graphics.Bitmap
import fhnw.emoba.blockbuster.data.models.MovieDetail
import fhnw.emoba.blockbuster.data.models.MovieDiscoveryResult
import fhnw.emoba.blockbuster.data.models.MultiSearchResult
import fhnw.emoba.blockbuster.data.models.PopularPeopleResult
import fhnw.emoba.blockbuster.data.models.VideoResult
import org.json.JSONObject
import java.net.URI

val DEFAULT_ICON = Bitmap.createBitmap(
    120,
    120,
    Bitmap.Config.ALPHA_8
)

open class TMDBService {
    // https://developer.themoviedb.org/reference/intro/getting-started 
    val accessToken = "GET_YOUR_OWN"
    val baseUrl = "https://api.themoviedb.org"
    fun discoverMovies(): MovieDiscoveryResult {
        val url = URI.create("$baseUrl/3/discover/movie")
        var result = content(
            url.toString(), hashMapOf(
                "Authorization" to "Bearer $accessToken"
            )
        )
        return MovieDiscoveryResult(JSONObject(result))
    }


    fun movieDetail(id: Int): MovieDetail {
        val url = URI.create("$baseUrl/3/movie/$id?language=en-US")
        var result = content(
            url.toString(), hashMapOf(
                "Authorization" to "Bearer $accessToken"
            )
        )
        return MovieDetail(JSONObject(result))
    }

    fun popularPeople(): PopularPeopleResult {
        val url = URI.create("$baseUrl/3/person/popular")
        var result = content(
            url.toString(), hashMapOf(
                "Authorization" to "Bearer $accessToken"
            )
        )
        return PopularPeopleResult(JSONObject(result))
    }

    fun multiSearch(query: String): MultiSearchResult {
        var queryUriEncoded = java.net.URLEncoder.encode(query, "UTF-8")
        val url = URI.create("$baseUrl/3/search/multi?query=$queryUriEncoded&include_adult=false&language=en-US&page=1")
        val result = content(
            url.toString(), hashMapOf(
                "Authorization" to "Bearer $accessToken"
            )
        )
        return MultiSearchResult(JSONObject(result))
    }

    fun getPoster(path: String): Bitmap {
        return try {
            val url = URI.create("https://image.tmdb.org/t/p/w600_and_h900_face$path")
            bitmap(url.toString())
        } catch (e: Exception) {
            DEFAULT_ICON
        }
    }

    fun movieVideos(movieId: Int): VideoResult {
        val url = URI.create("$baseUrl/3/movie/$movieId/videos")
        val result = content(
            url.toString(), hashMapOf(
                "Authorization" to "Bearer $accessToken"
            )
        )
        return VideoResult(JSONObject(result))
    }

}