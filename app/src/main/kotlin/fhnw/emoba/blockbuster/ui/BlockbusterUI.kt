package fhnw.emoba.blockbuster.ui

import androidx.compose.animation.Crossfade
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fhnw.emoba.blockbuster.data.TMDBService
import fhnw.emoba.blockbuster.model.BlockbusterModel
import fhnw.emoba.blockbuster.model.FavoritesModel
import fhnw.emoba.blockbuster.model.HomeModel
import fhnw.emoba.blockbuster.model.MovieDetailModel
import fhnw.emoba.blockbuster.model.PeopleModel
import fhnw.emoba.blockbuster.model.Screen
import fhnw.emoba.blockbuster.ui.components.LoadingSpinner
import fhnw.emoba.blockbuster.ui.screens.FavoriteScreen
import fhnw.emoba.blockbuster.ui.screens.HomeScreen
import fhnw.emoba.blockbuster.ui.screens.MovieDetailScreen
import fhnw.emoba.blockbuster.ui.screens.PersonsScreen
import fhnw.emoba.blockbuster.ui.theme.MaterialAppTheme


@Composable
fun AppUI(model: BlockbusterModel) {
    with(model) {
        MaterialAppTheme(
            darkTheme,
        ) {
            Crossfade(targetState = screenState.screen) { screen ->
                when (screen) {
                    Screen.HOME -> {
                        val homeModel = screenState.model as? HomeModel
                        if (homeModel != null && !homeModel.isLoading) {
                            HomeScreen(homeModel)
                        } else {
                            LoadingSpinner()
                        }
                    }

                    Screen.MOVIE_DETAIL -> {
                        val movieDetailModel = screenState.model as? MovieDetailModel
                        if (movieDetailModel != null && !movieDetailModel.isLoading) {
                            MovieDetailScreen(movieDetailModel)
                        } else {
                            LoadingSpinner()
                        }
                    }

                    Screen.PEOPLE -> {
                        val peopleModel = screenState.model as? PeopleModel
                        if (peopleModel != null && !peopleModel.isLoading) {
                            PersonsScreen(peopleModel)
                        } else {
                            LoadingSpinner()
                        }
                    }

                    Screen.FAVORITES -> {
                        val favoritesModel = screenState.model as? FavoritesModel
                        if (favoritesModel != null && !favoritesModel.isLoading) {
                            FavoriteScreen(favoritesModel)
                        } else {
                            LoadingSpinner()
                        }

                    }

                    else -> Text("Screen not implemented yet")
                }
            }
        }
    }
}
