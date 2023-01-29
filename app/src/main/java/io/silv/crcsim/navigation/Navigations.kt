package io.silv.crcsim.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun Navigations(
    selectedItem: Int,
    navItems: List<Pair<String, ImageVector>>,
    onSelected: (Int) -> Unit,
    content: @Composable RowScope.() -> Unit
) {
   Row(
       modifier = Modifier.fillMaxSize(),
       verticalAlignment = Alignment.Top,
       horizontalArrangement = Arrangement.Start
   ) {
       NavigationRail {
           navItems.forEachIndexed { index, (item, icon) ->
               NavigationRailItem(
                   icon = { Icon(icon, contentDescription = item) },
                   label = { Text(item) },
                   selected = selectedItem == index,
                   onClick = { onSelected(index) }
               )
           }
       }
       content()
   }
}