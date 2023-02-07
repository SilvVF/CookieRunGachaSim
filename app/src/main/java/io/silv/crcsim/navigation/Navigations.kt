package io.silv.crcsim.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.silv.crcsim.R
import io.silv.crcsim.ui.theme.DarkBlueNavRail

data class NavItem(
    val label: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter
)

@Composable
fun Navigations(
    selectedItem: Int,
    navItems: List<NavItem>,
    onSelected: (Int) -> Unit,
    content: @Composable RowScope.() -> Unit
) {
   Row(
       modifier = Modifier.fillMaxSize(),
       verticalAlignment = Alignment.Top,
       horizontalArrangement = Arrangement.Start
   ) {

       val screenWidth = LocalConfiguration.current.screenWidthDp

       NavigationRail(
           containerColor = DarkBlueNavRail,
           modifier = Modifier.width((screenWidth * 0.12f).dp)
       ) {
           Image(painter = painterResource(R.drawable.top_oven_icon), contentDescription = "oven_small")
           navItems.forEachIndexed { index, (label, selectedIcon, unselectedIcon) ->
               NavigationRailItem(
                   icon = {
                       Image(
                           painter = if (selectedItem == index) selectedIcon
                                     else unselectedIcon,
                           contentDescription = label
                       )
                   },
                   colors = NavigationRailItemDefaults.colors(
                       selectedIconColor = Color.Transparent,
                       unselectedIconColor = Color.Transparent,
                       selectedTextColor = Color.Transparent,
                       indicatorColor = Color.Transparent
                   ),
                   label = { Text(label) },
                   selected = selectedItem == index,
                   onClick = { onSelected(index) }
               )
           }
       }
       content()
   }
}