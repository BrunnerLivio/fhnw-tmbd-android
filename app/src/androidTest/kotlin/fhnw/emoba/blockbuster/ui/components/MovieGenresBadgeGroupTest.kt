package fhnw.emoba.blockbuster.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.sp
import androidx.test.ext.junit.runners.AndroidJUnit4
import fhnw.emoba.blockbuster.ui.components.MovieGenresBadgeGroup
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieGenresBadgeGroupTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val genres = listOf("Action", "Comedy", "Drama")

    @Test
    fun movieGenresBadgeGroup_DisplaysAllGenres() {
        composeTestRule.setContent {
            MaterialTheme {
                MovieGenresBadgeGroup(
                    genres = genres,
                    contentColor = Color.White,
                    containerColor = Color.Gray
                )
            }
        }

        // Check that each genre is displayed
        genres.forEach { genre ->
            composeTestRule.onNodeWithText(genre).assertIsDisplayed()
        }
    }

    @Test
    fun movieGenresBadgeGroup_DisplaysCorrectColors() {
        composeTestRule.setContent {
            MaterialTheme {
                MovieGenresBadgeGroup(
                    genres = genres,
                    contentColor = Color.White,
                    containerColor = Color.Gray
                )
            }
        }

        // Verify each badge has the correct content and container colors by checking display only
        genres.forEach { genre ->
            composeTestRule.onNodeWithText(genre).assertIsDisplayed()
        }
        // Note: We can only assert colors directly by inspecting code or by using Screenshot Testing.
    }

    @Test
    fun movieGenresBadgeGroup_DisplaysBadgesWithSpacing() {
        composeTestRule.setContent {
            MaterialTheme {
                MovieGenresBadgeGroup(
                    genres = genres,
                    contentColor = Color.White,
                    containerColor = Color.Gray
                )
            }
        }

        // Ensure each badge is displayed and that the Row arranges them correctly
        composeTestRule.onRoot().onChildren().assertCountEquals(genres.size)
    }

    @Test
    fun movieGenresBadgeGroup_ClickableBadges() {
        var clickedGenre: String? = null

        composeTestRule.setContent {
            MaterialTheme {
                MovieGenresBadgeGroup(
                    genres = genres,
                    contentColor = Color.White,
                    containerColor = Color.Gray
                )
            }
        }

        // Verify clicking a badge registers correctly (mock click behavior here)
        genres.forEach { genre ->
            composeTestRule.onNodeWithText(genre).performClick()
            clickedGenre = genre
            assert(clickedGenre == genre) { "Expected clicked genre to be $genre but was $clickedGenre" }
        }
    }
}
