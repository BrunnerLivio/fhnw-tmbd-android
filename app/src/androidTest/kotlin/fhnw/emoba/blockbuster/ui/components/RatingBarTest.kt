package fhnw.emoba.blockbuster.ui.components

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import org.junit.Rule
import org.junit.Test

class RatingBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ratingBar_DisplaysThreeFilledOneHalfAndOneEmptyStar_WhenRatingIsSeven() {
        composeTestRule.setContent {
            RatingBar(rating = 7.0)
        }

        composeTestRule.onAllNodesWithTag("FilledStar")
            .assertCountEquals(3)

        composeTestRule.onAllNodesWithTag("HalfStar")
            .assertCountEquals(1)

        composeTestRule.onAllNodesWithTag("EmptyStar")
            .assertCountEquals(1)
    }

    @Test
    fun ratingBar_DisplaysAllEmptyStars_WhenRatingIsZero() {
        composeTestRule.setContent {
            RatingBar(rating = 0.0)
        }

        composeTestRule.onAllNodesWithTag("EmptyStar")
            .assertCountEquals(5)

        composeTestRule.onAllNodesWithTag("FilledStar")
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithTag("HalfStar")
            .assertCountEquals(0)
    }

    @Test
    fun ratingBar_DisplaysAllFilledStars_WhenRatingIsMax() {
        composeTestRule.setContent {
            RatingBar(rating = 10.0)
        }

        composeTestRule.onAllNodesWithTag("FilledStar")
            .assertCountEquals(5)

        composeTestRule.onAllNodesWithTag("EmptyStar")
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithTag("HalfStar")
            .assertCountEquals(0)
    }


    @Test
    fun ratingBar_DisplaysOneFilledOneHalfAndThreeEmptyStars_WhenRatingIsThreePointFive() {
        composeTestRule.setContent {
            RatingBar(rating = 3.5)
        }

        composeTestRule.onAllNodesWithTag("FilledStar")
            .assertCountEquals(1)

        composeTestRule.onAllNodesWithTag("HalfStar")
            .assertCountEquals(1)

        composeTestRule.onAllNodesWithTag("EmptyStar")
            .assertCountEquals(3)
    }

    @Test
    fun ratingBar_DisplaysTwoFilledOneHalfAndTwoEmptyStars_WhenRatingIsFive() {
        composeTestRule.setContent {
            RatingBar(rating = 5.0)
        }

        composeTestRule.onAllNodesWithTag("FilledStar")
            .assertCountEquals(2)

        composeTestRule.onAllNodesWithTag("HalfStar")
            .assertCountEquals(1)

        composeTestRule.onAllNodesWithTag("EmptyStar")
            .assertCountEquals(2)
    }
}