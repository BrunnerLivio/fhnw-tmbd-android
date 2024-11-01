package fhnw.emoba.blockbuster.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier,
    filledColor: Color = MaterialTheme.colorScheme.primary,
    emptyColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
) {
    val stars = (rating / 2).coerceIn(0.0, 5.0)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (i in 1..5) {
            when {
                i <= stars.toInt() -> {
                    // Fully filled star
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = filledColor,
                        modifier = Modifier.testTag("FilledStar")
                    )
                }

                i == stars.toInt() + 1 && stars % 1 >= 0.5 -> {
                    // Half-filled star
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.StarHalf,
                        contentDescription = null,
                        tint = filledColor,
                        modifier = Modifier.testTag("HalfStar")
                    )
                }

                else -> {
                    // Empty star
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = emptyColor,
                        modifier = Modifier.testTag("EmptyStar")
                    )
                }
            }
        }
    }
}
