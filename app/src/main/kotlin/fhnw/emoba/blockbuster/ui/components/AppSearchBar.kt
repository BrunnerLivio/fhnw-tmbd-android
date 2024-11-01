package fhnw.emoba.blockbuster.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import fhnw.emoba.blockbuster.data.models.MovieItem
import fhnw.emoba.blockbuster.model.Screen
import fhnw.emoba.blockbuster.model.SearchModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(
    model: SearchModel,
    searchBarColors: SearchBarColors = SearchBarDefaults.colors(),
    textFieldColors: TextFieldColors = TextFieldDefaults.colors(),
    iconColor: Color = MaterialTheme.colorScheme.primary
) {
    val textFieldState = rememberTextFieldState()
    var expanded by rememberSaveable { mutableStateOf(false) }
    val searchQuery by model.searchQuery.collectAsState()

    with(model) {
        Box {
            SearchBar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .testTag("AppSearchBar")
                    .semantics { traversalIndex = 0f },
                colors = searchBarColors,
                inputField = {
                    SearchBarDefaults.InputField(
                        onSearch = { expanded = false },
                        colors = textFieldColors,
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        placeholder = { Text("Search movies and people...") },
                        leadingIcon = {
                            Icon(
                                if (expanded) Icons.AutoMirrored.Default.ArrowBack else Icons.Default.Search,
                                contentDescription = null,
                                tint = iconColor,
                                modifier = Modifier
                                    .testTag("LeadingIcon")
                                    .clickable {
                                        if (expanded) {
                                            textFieldState.setTextAndPlaceCursorAtEnd("")
                                        }
                                        expanded = !expanded
                                    }
                            )
                        },
                        trailingIcon = {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null,
                                tint = iconColor,
                                modifier = Modifier
                                    .testTag("TrailingIcon")
                                    .clickable {
                                        onSearchQueryChange("")
                                    }
                            )
                        },
                        query = searchQuery,
                        onQueryChange = {
                            textFieldState.setTextAndPlaceCursorAtEnd(it);
                            onSearchQueryChange(it)
                        },
                        modifier = Modifier.testTag("SearchInputField"),
                    )
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
            ) {
                SearchResult(model)
            }
        }
    }
}

@Composable
fun SearchResult(model: SearchModel) {
    with(model) {
        LazyColumn() {
            items(searchItems) { item ->
                ListItem(
                    headlineContent = { Text(item.getDisplayName()) },
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                if (item is MovieItem) {
                                    context.selectedMovieItem = item
                                    context.navigateTo(Screen.MOVIE_DETAIL)
                                }
                            }
                        )
                        .testTag("SearchResultItem"),
                    leadingContent = {
                        Image(
                            item.image, item.getDisplayName(),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(64.dp)
                                .height(64.dp)
                        )
                    },
                    trailingContent = {
                        if(item is MovieItem) {
                            FavoriteIcon(
                                movieItem = item,
                                favoriteColor = MaterialTheme.colorScheme.primary,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                )
            }
        }
    }
}