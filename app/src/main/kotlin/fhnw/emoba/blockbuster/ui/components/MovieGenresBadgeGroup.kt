package fhnw.emoba.blockbuster.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Badge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MovieGenresBadgeGroup(genres: List<String>, contentColor: Color, containerColor: Color) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.wrapContentSize()
        ) {
            genres.forEach {
                Badge(
                    content = {
                        Text(
                            it,
                            modifier = Modifier.padding(4.dp)
                        )
                    },
                    contentColor = contentColor,
                    containerColor = containerColor,
                )
            }
        }
    }
}