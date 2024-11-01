package fhnw.emoba.blockbuster.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fhnw.emoba.blockbuster.model.MovieDetailModel
import fhnw.emoba.blockbuster.model.Screen
import fhnw.emoba.blockbuster.ui.components.FavoriteIcon
import fhnw.emoba.blockbuster.ui.components.MovieGenresBadgeGroup
import fhnw.emoba.blockbuster.ui.components.RatingBar
import fhnw.emoba.blockbuster.ui.components.YoutubePlayer
import fhnw.emoba.blockbuster.ui.layout.BottomBar
import fhnw.emoba.blockbuster.ui.layout.TopBar

@Composable
fun MovieDetailScreen(model: MovieDetailModel) {

    with(model) {
        Scaffold(
            topBar = {
                TopBar(
                    model.context,
                    content = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier
                                    .padding(16.dp)
                                    .clickable {
                                        model.context.navigateTo(Screen.HOME)
                                    },
                                tint = palette.fgColor,
                            )
                            FavoriteIcon(context.selectedMovieItem!!, color = palette.fgColor, favoriteColor = palette.accentColor)
                        }


                    },
                    surfaceColor = palette.bgColor,
                )
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxHeight()
                        .padding(it)
                )
                {
                    MoveDetailBody(model)
                }
            },
            bottomBar = {
                BottomBar(
                    model.context,
                    containerColor = palette.bgColor,
                    contentColor = palette.fgColor,
                    navigationItemColors = NavigationBarItemDefaults.colors(
                        unselectedIconColor = palette.fgColor,
                        selectedIconColor = palette.fgColor,
                        indicatorColor = palette.fgColor,
                        selectedTextColor = palette.fgColor,
                        unselectedTextColor = palette.fgColor,
                    ),
                )
            }
        )
    }

}

@Composable
private fun MoveDetailBody(model: MovieDetailModel) {
    with(model) {
        val movie = movie ?: return

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .defaultMinSize(minHeight = 500.dp),
            color = palette.bgColor,
            contentColor = palette.fgColor
        ) {
            LazyColumn(
                modifier = Modifier
                    .defaultMinSize(minHeight = 500.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    HeaderImage(model)
                }
                item {
                    Content(
                        {
                            Text(
                                movie.title,
                                style = MaterialTheme.typography.headlineLarge,
                                color = palette.fgColor,
                            )
                        }, 100
                    )
                }

                item {
                    Content(
                        {
                            MovieGenresBadgeGroup(
                                movie.genres.map { it.name },
                                contentColor = palette.accentColor,
                                containerColor = palette.fgColor,
                            )
                        }, 200
                    )
                }

                item {
                    Content(
                        {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                MovieInformation(model)
                            }

                        }, 300
                    )
                }

                item {
                    movie.let {
                        Content(
                            {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                )
                                {
                                    RatingBar(
                                        it.voteAverage,
                                        emptyColor = palette.fgColor.copy(alpha = 0.1f)
                                    )
                                }
                            }, 400
                        )
                    }
                }

                item {
                    Content(
                        {
                            Text(
                                movie.tagline,
                                color = palette.accentColor,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                            )
                        },
                        500
                    )
                }

                item {
                    Content(
                        {
                            Text(movie.overview, style = MaterialTheme.typography.bodyMedium)
                        },
                        500
                    )
                }
                item {
                    Content(
                        {
                            Text(
                                "Trailers",
                                color = palette.accentColor,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                            )
                        }, 600
                    )
                }
                items(videos) { video ->
                    Content(
                        {
                            YoutubePlayer(video)
                        }, 700
                    )
                }
            }
        }
    }
}

@Composable
fun MovieInformation(model: MovieDetailModel) {
    with(model) {
        val movie = movie ?: return
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InformationBox(
                model,
                "Release Date",
                movie.releaseDate,
            )
            InformationBox(
                model,
                "Runtime",
                movie.runtime.toString() + " min",
            )
            InformationBox(
                model,
                "Status",
                movie.status,
            )
        }
    }
}

@Composable
fun Content(content: @Composable () -> Unit, delay: Long) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
    ) {
        content()
    }

}

@Composable
fun InformationBox(model: MovieDetailModel, title: String, content: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = model.palette.fgColor
        )
        Text(
            content,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            color = model.palette.fgColor
        )
    }
}

@Composable
fun HeaderImage(model: MovieDetailModel) {
    with(model) {
        val movie = movie ?: return

        Box(
            modifier = Modifier.height(400.dp)
        ) {
            Image(
                bitmap = posterImage,
                contentDescription = movie.title,
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
    }

}