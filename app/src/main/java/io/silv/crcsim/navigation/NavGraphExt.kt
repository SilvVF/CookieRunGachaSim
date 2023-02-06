package io.silv.crcsim.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import io.silv.crcsim.feat_gacha.GachaNavHost
import io.silv.crcsim.feat_gacha.compose.GachaRoute


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.gachaScreen(
    route: String,
    navController: NavController
) = this.composable(
    route,
    enterTransition = {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
    },
    exitTransition = {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
    },
    popEnterTransition = {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
    },
    popExitTransition = {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))

    }
) { GachaNavHost() }

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableFadeAnim(
    route: GachaRoute,
    navController: NavController,
    content: @Composable (navController: NavController) -> Unit
) = this.composable(
    route.route,
    enterTransition = {
        fadeIn()
    },
    exitTransition = {
       fadeOut()
    },
    popEnterTransition = {
       fadeIn()
    },
    popExitTransition = {
        fadeOut()
    }
) { content(navController) }


fun NavController.toGachaDest(
    gachaRoute: GachaRoute
) = this.navigate(gachaRoute.route)