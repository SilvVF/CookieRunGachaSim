package io.silv.crcsim.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import io.silv.crcsim.feat_treasure_gacha.compose.screens.TreasureGachaScreen
import io.silv.crcsim.feat_ui_inventory.InventoryScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedNavigation(
    navController: NavHostController,
    start: String,
    navRailVisible: (Boolean) -> Unit
) {

    AnimatedNavHost(navController = navController, startDestination = start) {
        gachaScreen(
            route = "Cookies",
            gachaInProgress = { inProgress -> navRailVisible(!inProgress) },
            navController = navController,
        )

        slideInFadeOutComposable("Treasure") {
           TreasureGachaScreen(
               gachaInProgress = { inProgress ->
                   navRailVisible(!inProgress)
               }
           )
        }

        slideInFadeOutComposable("Inventory") {
             InventoryScreen()
        }
    }
}