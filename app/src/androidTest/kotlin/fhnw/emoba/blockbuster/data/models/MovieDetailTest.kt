package fhnw.emoba.blockbuster.data.models

import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDetailTest {

    @Test
    fun testMovieDetailInitialization() {
        // Create a sample JSON object
        val jsonString = """
            {
                "adult": false,
                "backdrop_path": "/path/to/backdrop.jpg",
                "id": 123,
                "original_language": "en",
                "original_title": "Test Movie",
                "overview": "This is a test movie.",
                "popularity": 10.5,
                "poster_path": "/path/to/poster.jpg",
                "release_date": "2024-11-01",
                "title": "Test Movie Title",
                "video": false,
                "vote_average": 7.5,
                "vote_count": 100,
                "belongs_to_collection": null,
                "budget": 5000000,
                "genres": [
                    {"id": 1, "name": "Action"},
                    {"id": 2, "name": "Adventure"}
                ],
                "homepage": "http://testmovie.com",
                "imdb_id": "tt1234567",
                "origin_country": ["US"],
                "production_companies": [
                    {"id": 1, "name": "Test Production"}
                ],
                "production_countries": [
                    {"iso_3166_1": "US", "name": "United States"}
                ],
                "revenue": 10000000,
                "runtime": 120,
                "spoken_languages": [
                    {"iso_639_1": "en", "name": "English"}
                ],
                "status": "Released",
                "tagline": "This is a tagline."
            }
        """

        val jsonObject = JSONObject(jsonString)

        val movieDetail = MovieDetail(jsonObject)

        assertEquals(false, movieDetail.adult)
        assertEquals("/path/to/backdrop.jpg", movieDetail.backdropPath)
        assertEquals(123, movieDetail.id)
        assertEquals("en", movieDetail.originalLanguage)
        assertEquals("Test Movie", movieDetail.originalTitle)
        assertEquals("This is a test movie.", movieDetail.overview)
        assertEquals(10.5, movieDetail.popularity, 0.0)
        assertEquals("/path/to/poster.jpg", movieDetail.posterPath)
        assertEquals("2024-11-01", movieDetail.releaseDate)
        assertEquals("Test Movie Title", movieDetail.title)
        assertEquals(false, movieDetail.video)
        assertEquals(7.5, movieDetail.voteAverage, 0.0)
        assertEquals(100, movieDetail.voteCount)
        assertEquals(5000000, movieDetail.budget)
        assertEquals("http://testmovie.com", movieDetail.homepage)
        assertEquals("tt1234567", movieDetail.imdbId)
        assertEquals(10000000, movieDetail.revenue)
        assertEquals(120, movieDetail.runtime)
        assertEquals("Released", movieDetail.status)
        assertEquals("This is a tagline.", movieDetail.tagline)

        assertEquals(2, movieDetail.genres.size)
        assertEquals(1, movieDetail.genres[0].id)
        assertEquals("Action", movieDetail.genres[0].name)
        assertEquals(2, movieDetail.genres[1].id)
        assertEquals("Adventure", movieDetail.genres[1].name)
    }
}
