package fhnw.emoba.blockbuster.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fhnw.emoba.blockbuster.model.HomeModel
import fhnw.emoba.blockbuster.ui.components.AppSearchBar
import fhnw.emoba.blockbuster.ui.components.MovieCard
import fhnw.emoba.blockbuster.ui.layout.BottomBar
import fhnw.emoba.blockbuster.ui.layout.TopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(model: HomeModel) {

    Scaffold(
        topBar = { TopBar(model.context, content = { AppSearchBar(model.context.search) }) },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
            )
            {
                DefaultBody(model)
            }
        },
        bottomBar = {
            BottomBar(model.context)
        }
    )
}

@Composable
fun DefaultBody(model: HomeModel) {
    val scope = rememberCoroutineScope()
    with(model) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            LazyColumn(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(movieItems) { movie ->
                    MovieCard(
                        movieItem = movie,
                        onClick = { scope.launch { gotoMovieDetail(movie) } })
                }
            }

        }
    }
}
