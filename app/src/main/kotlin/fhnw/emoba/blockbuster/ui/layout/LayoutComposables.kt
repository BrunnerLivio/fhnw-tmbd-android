package fhnw.emoba.blockbuster.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fhnw.emoba.R
import fhnw.emoba.blockbuster.model.AppTab
import fhnw.emoba.blockbuster.model.BlockbusterModel

@Composable
fun TopBar(
    model: BlockbusterModel,
    content: @Composable () -> Unit = {},
    surfaceColor: Color = MaterialTheme.colorScheme.background,
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        TMDBBar(model)
        Surface(Modifier.fillMaxWidth(), color = surfaceColor) {
            content()
        }
    }

}

@Composable
fun TMDBBar(model: BlockbusterModel) {
    if (model.showTmdbBar) {
        Surface {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tmdb_logo),
                    contentDescription = "TMDB Logo",
                    modifier = Modifier
                        .height(50.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.clickable(
                        onClick = {
                            model.showTmdbBar = false
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                    )
                    Text("Fuck the TMDB Logo")
                }
            }
        }
    }

}

@Composable
fun BottomBar(
    model: BlockbusterModel,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    navigationItemColors: NavigationBarItemColors = NavigationBarItemDefaults.colors(),
) {
    with(model) {
        NavigationBar(containerColor = containerColor, contentColor = contentColor) {
            AppTab.entries.forEach { item ->
                NavigationBarItem(
                    colors = navigationItemColors,
                    icon = {
                        Icon(
                            if (item == selectedTab) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title,
                        )
                    },
                    label = { Text(item.title) },
                    selected = selectedTab == item,
                    onClick = {
                        selectedTab = item;
                        selectedTab.navigationFn(model)
                    }
                )
            }
        }
    }
}
