package fhnw.emoba.blockbuster.ui.components;

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import fhnw.emoba.blockbuster.data.DEFAULT_ICON
import fhnw.emoba.blockbuster.data.models.MovieItem
import org.json.JSONObject

import org.junit.Rule
import org.junit.Test

public class MovieCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val movieJson = JSONObject(
        """
        {
            "adult": false,
            "backdrop_path": "/backdrop.jpg",
            "genre_ids": [28, 12, 16],
            "id": 123,
            "original_language": "en",
            "original_title": "Mock Movie",
            "overview": "This is a mock overview of the movie.",
            "popularity": 1234.56,
            "poster_path": "/poster.jpg",
            "release_date": "2023-10-10",
            "title": "Mock Movie Title",
            "video": false,
            "vote_average": 7.8,
            "vote_count": 987
        }
        """
    )

    private val movieItem = MovieItem(movieJson).apply {
        posterImage = DEFAULT_ICON.asImageBitmap()
    }

    @Test
    fun movieCard_DisplaysTitleAndOverview() {
        composeTestRule.setContent {
            MaterialTheme {
                MovieCard(movieItem = movieItem)
            }
        }

        composeTestRule.onNodeWithText("Mock Movie Title").assertIsDisplayed()

        composeTestRule.onNodeWithText("This is a mock overview of the movie.").assertIsDisplayed()
    }

    @Test
    fun movieCard_DisplaysImage() {
        composeTestRule.setContent {
            MaterialTheme {
                MovieCard(movieItem = movieItem)
            }
        }

        composeTestRule.onNodeWithContentDescription("Mock Movie Title").assertIsDisplayed()
    }

    @Test
    fun movieCard_OnClickTriggersCallback() {
        var clicked = false

        composeTestRule.setContent {
            MaterialTheme {
                MovieCard(movieItem = movieItem, onClick = { clicked = true })
            }
        }

        composeTestRule.onNodeWithContentDescription("Mock Movie Title").performClick()

        assert(clicked) { "The onClick callback was not triggered." }
    }
}
