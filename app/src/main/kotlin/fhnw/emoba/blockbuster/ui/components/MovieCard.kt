package fhnw.emoba.blockbuster.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fhnw.emoba.blockbuster.data.models.MovieItem

@Composable
fun MovieCard(movieItem: MovieItem, onClick: () -> Unit = {}) {
    with(movieItem) {
        Surface(
            onClick = onClick,
            color = palette.bgColor,
            contentColor = palette.fgColor,
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier.height(400.dp)
                ) {
                    Image(
                        bitmap = movieItem.posterImage,
                        contentDescription = movieItem.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .offset(x = 0.dp, y = 0.dp)
                    )
                    Box(
                        modifier = Modifier
                            .offset(x = 0.dp, y = 0.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, palette.bgColor)
                                )
                            )
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RatingBar(
                            rating = movieItem.voteAverage,
                            filledColor = palette.accentColor,
                            emptyColor = palette.fgColor
                        )
                        FavoriteIcon(
                            movieItem,
                            color = palette.fgColor,
                            favoriteColor = palette.accentColor
                        )
                    }
                    Text(
                        text = movieItem.title,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium,
                        color = palette.accentColor
                    )
                    Text(
                        text = movieItem.overview,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Justify,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

            }
        }
    }
}
