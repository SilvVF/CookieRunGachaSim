package io.silv.crcsim.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.*
import com.google.accompanist.navigation.animation.composable
import io.silv.crcsim.feat_artifact_gacha.compose.ArtifactRoute
import io.silv.crcsim.feat_cookie_gacha.compose.GachaNavHost
import io.silv.crcsim.feat_cookie_gacha.compose.GachaRoute


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.gachaScreen(
    route: String,
    gachaInProgress: (Boolean) -> Unit,
    navController: NavController
) = this.composable(
    route,
    enterTransition = {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
    },
    exitTransition = {
        fadeOut(animationSpec = tween(700))
    },
    popEnterTransition = {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
    },
    popExitTransition = {
        fadeOut(animationSpec = tween(700))

    }
) { GachaNavHost { inProgress -> gachaInProgress(inProgress)  } }

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableFadeAnim(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    navController: NavController,
    content: @Composable (navController: NavController, backStackEntry: NavBackStackEntry) -> Unit
) = this.composable(
    route = route,
    arguments = arguments,
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
) { navBackStackEntry ->  content(navController, navBackStackEntry) }


fun NavController.toGachaDest(
    gachaRoute: GachaRoute
) = when(gachaRoute) {
        is GachaRoute.Reveal -> this.navigate(
            "reveal/${gachaRoute.idx}"
        )
        is GachaRoute.RevealIdle -> this.navigate(
            "reveal-idle/${gachaRoute.idx}"
        )
        else -> this.navigate(gachaRoute.route)
    }

fun NavController.toArtifactDest(
    artifactRoute: ArtifactRoute
) = when(artifactRoute) {
    is ArtifactRoute.Reveal -> this.navigate(
        "reveal/${artifactRoute.idx}"
    )
    is ArtifactRoute.RevealIdle -> this.navigate(
        "reveal-idle/${artifactRoute.idx}"
    )
    else -> this.navigate(artifactRoute.route)
}
