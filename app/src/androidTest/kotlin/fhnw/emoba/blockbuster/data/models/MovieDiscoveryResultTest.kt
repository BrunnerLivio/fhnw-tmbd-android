package fhnw.emoba.blockbuster.data.models

import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDiscoveryResultTest {

    @Test
    fun testMovieDiscoveryResultInitialization() {
        val jsonString = """
            {
                "page": 1,
                "total_results": 100,
                "total_pages": 10,
                "results": [
                    {
                        "adult": false,
                        "backdrop_path": "/path/to/backdrop1.jpg",
                        "id": 1,
                        "original_language": "en",
                        "original_title": "Test Movie 1",
                        "overview": "Overview of Test Movie 1",
                        "popularity": 5.0,
                        "poster_path": "/path/to/poster1.jpg",
                        "release_date": "2024-01-01",
                        "title": "Test Movie Title 1",
                        "video": false,
                        "vote_average": 8.0,
                        "vote_count": 50
                    },
                    {
                        "adult": false,
                        "backdrop_path": "/path/to/backdrop2.jpg",
                        "id": 2,
                        "original_language": "en",
                        "original_title": "Test Movie 2",
                        "overview": "Overview of Test Movie 2",
                        "popularity": 7.0,
                        "poster_path": "/path/to/poster2.jpg",
                        "release_date": "2024-02-01",
                        "title": "Test Movie Title 2",
                        "video": false,
                        "vote_average": 7.5,
                        "vote_count": 30
                    }
                ]
            }
        """

        val jsonObject = JSONObject(jsonString)

        val movieDiscoveryResult = MovieDiscoveryResult(jsonObject)

        assertEquals(1, movieDiscoveryResult.page)
        assertEquals(100, movieDiscoveryResult.totalResults)
        assertEquals(10, movieDiscoveryResult.totalPages)

        val items = movieDiscoveryResult.results
        assertEquals(2, items.size)

        assertEquals(1, items[0].id)
        assertEquals("Test Movie Title 1", items[0].title)
        assertEquals("Overview of Test Movie 1", items[0].overview)
        assertEquals("/path/to/poster1.jpg", items[0].posterPath)

        assertEquals(2, items[1].id)
        assertEquals("Test Movie Title 2", items[1].title)
        assertEquals("Overview of Test Movie 2", items[1].overview)
        assertEquals("/path/to/poster2.jpg", items[1].posterPath)
    }
}
