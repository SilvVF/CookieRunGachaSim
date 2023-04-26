package io.silv.crcsim.feat_cookie_gacha.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.navArgument
import coil.imageLoader
import coil.request.ImageRequest
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.silv.crcsim.feat_cookie_gacha.CookieGachaViewModel
import io.silv.crcsim.feat_cookie_gacha.compose.screens.*
import io.silv.crcsim.navigation.composableFadeAnim
import io.silv.crcsim.navigation.toGachaDest
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GachaNavHost(
    viewModel: CookieGachaViewModel = koinViewModel(),
    gachaInProgress: (inProgress: Boolean) -> Unit
) {

    val navHostController = rememberAnimatedNavController()
    val state by viewModel.collectAsState()
    val ctx = LocalContext.current

    val currentImageRequestList by remember(state.pull) {
        derivedStateOf {
            state.pull.result.map {
                ImageRequest.Builder(ctx)
                    .data(
                        if (it.full) {
                           it.cookie.gachaBgUrl
                        } else it.cookie.soulstoneUrl
                    )
                    .crossfade(true)
                    .build()
            }
        }
    }


    LaunchedEffect(state.pull) {

        fun createImageRequest(url: String): ImageRequest =
            ImageRequest.Builder(ctx)
                .data(url)
                .build()

        val requestLists = state.pull.result.map {
            listOf(
                if (it.full) {
                    createImageRequest(it.cookie.gachaBgUrl)
                    createImageRequest(it.cookie.imageUrl)
                } else {
                    createImageRequest(it.cookie.soulstoneUrl)
                }
            )
        }
        requestLists.forEach { list ->
            list.forEach { request ->
                ctx.imageLoader.enqueue(request)
            }
        }
    }



    AnimatedNavHost(navController = navHostController, startDestination = GachaRoute.Waiting.route) {

        composableFadeAnim(
            GachaRoute.Waiting.route,
            navController = navHostController
        ) { navController, _ ->

            LaunchedEffect(key1 = true) {
                gachaInProgress(false)
            }

            val history by viewModel.history.collectAsState(initial = emptyList())

            WaitingScreen(
                history = history,
                crystals = state.crystalsSpent,
                onDraw1Click = {
                    viewModel.handleDraw1Click()
                    gachaInProgress(true)
                    navController.toGachaDest(GachaRoute.Start)
                },
                onDraw10Click = {
                    viewModel.handleDraw10Click()
                    gachaInProgress(true)
                    navController.toGachaDest(GachaRoute.Start)
                },
                changeFilter = { filter ->
                    viewModel.changeFilter(filter)
                }
            )
        }

        composableFadeAnim(
            GachaRoute.Start.route,
            navController = navHostController
        ) { navController, _ ->

            StartScreen { gachaRoute ->
                navController.toGachaDest(gachaRoute)
            }
        }

        composableFadeAnim(
            GachaRoute.Idle.route,
            navController = navHostController
        ) { navController, _ ->

            IdleScreen { gachaRoute ->
                navController.toGachaDest(gachaRoute)
            }
        }

        composableFadeAnim(
            GachaRoute.IdleEnd.route,
            navController = navHostController
        ) { navController, _ ->

           IdleEndScreen { gachaRoute ->
               navController.toGachaDest(gachaRoute)
           }
        }

        composableFadeAnim(
            route = GachaRoute.Reveal(0).route,
            navController = navHostController,
            arguments = listOf(navArgument("idx") { type = NavType.IntType })
        ) { navController, backStackEntry ->

            val idx = backStackEntry.arguments?.getInt("idx") ?: 0

            RevealScreen(
                cookieDraw = state.pull.result[idx],
                onNavigate = {
                    navController.toGachaDest(GachaRoute.RevealIdle(idx))
                }
            )
        }

        composableFadeAnim(
            route = GachaRoute.RevealIdle(0).route,
            navController = navHostController,
            arguments = listOf(navArgument("idx") { type = NavType.IntType })
        ) { navController, backStackEntry ->

            val idx = backStackEntry.arguments?.getInt("idx") ?: 0

            RevealIdleScreen(
                cookieDraw = state.pull.result[idx],
                onNavigate = {
                    navController.toGachaDest(
                        if (idx < state.pull.result.lastIndex)
                            GachaRoute.Reveal(idx + 1)
                        else
                            GachaRoute.End
                    )
                },
                imageRequest = currentImageRequestList[idx]
            )
        }

        composableFadeAnim(
            route = GachaRoute.End.route,
            navController = navHostController,
        ) { navController, _ ->

            EndScreen(pull = state.pull, currentImageRequestList) {
                navController.toGachaDest(GachaRoute.Waiting)
            }
        }
    }
}