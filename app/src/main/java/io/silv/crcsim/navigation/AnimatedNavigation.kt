package io.silv.crcsim.navigation


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost



@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedNavigation(
    navController: NavHostController,
    start: String
) {

    AnimatedNavHost(navController = navController, startDestination = start) {
        gachaScreen(
            route = "Gacha",
            navController
        )
    }
}