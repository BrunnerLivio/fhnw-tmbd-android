package fhnw.emoba.blockbuster.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import fhnw.emoba.blockbuster.data.models.PeopleItem
import fhnw.emoba.blockbuster.model.PeopleModel
import fhnw.emoba.blockbuster.ui.components.AppSearchBar
import fhnw.emoba.blockbuster.ui.layout.BottomBar
import fhnw.emoba.blockbuster.ui.layout.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonsScreen(model: PeopleModel) {
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
                PersonsBody(model)
            }
        },
        bottomBar = {
            BottomBar(model.context)
        }
    )
}

@Composable
fun PersonsBody(model: PeopleModel) {
    with(model) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxSize(),
            content = {
                items(peopleItems) { peopleItem ->
                    PeopleCard(
                        peopleItem = peopleItem
                    )
                }
            }
        )
    }

}

@Composable
fun PeopleCard(peopleItem: PeopleItem) {
    val height = peopleItem.id % 251 + 100;

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()

    ) {
        Box {
            Image(
                bitmap = peopleItem.profileImage,
                contentDescription = peopleItem.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height.dp),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.BottomEnd)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black)
                        )
                    ),
            ) {
                Text(
                    text = peopleItem.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}