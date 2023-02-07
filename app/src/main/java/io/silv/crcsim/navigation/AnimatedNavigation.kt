package io.silv.crcsim.navigation


import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


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

        composable("Artifacts",
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))

            }
        ) {
            Box(Modifier.fillMaxSize().clickable {
                navController.navigate("Cookies")
            }) {
                Text(text = "Artifact screen", Modifier.align(Alignment.Center))
            }
        }
    }
}