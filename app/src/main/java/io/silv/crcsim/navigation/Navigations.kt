package io.silv.crcsim.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import io.silv.crcsim.R
import io.silv.crcsim.ui.theme.DarkBlueNavRail


@Composable
fun Navigations(
    selectedItem: Int,
    navItems: List<Pair<String, Painter>>,
    onSelected: (Int) -> Unit,
    content: @Composable RowScope.() -> Unit
) {
   Row(
       modifier = Modifier.fillMaxSize(),
       verticalAlignment = Alignment.Top,
       horizontalArrangement = Arrangement.Start
   ) {
       NavigationRail(
           containerColor = DarkBlueNavRail
       ) {
           Image(painter = painterResource(R.drawable.top_oven_icon), contentDescription = "oven_small")
           navItems.forEachIndexed { index, (item, icon) ->
               NavigationRailItem(
                   icon = { Image(icon, contentDescription = item) },
                   label = { Text(item) },
                   selected = selectedItem == index,
                   onClick = { onSelected(index) }
               )
           }
       }
       content()
   }
}