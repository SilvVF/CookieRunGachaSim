package io.silv.crcsim.feat_gacha

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.silv.crcsim.R
import io.silv.crcsim.feat_gacha.compose.GachaRoute
import io.silv.crcsim.feat_gacha.compose.components.Player
import io.silv.crcsim.feat_gacha.compose.screens.*
import io.silv.crcsim.navigation.composableFadeAnim
import io.silv.crcsim.navigation.gachaScreen
import io.silv.crcsim.navigation.toGachaDest
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GachaNavHost(
    viewModel: GachaViewModel = koinViewModel(),
    gachaInProgress: (inProgress: Boolean) -> Unit
) {

    val navHostController = rememberAnimatedNavController()
    val state by viewModel.collectAsState()


    AnimatedNavHost(navController = navHostController, startDestination = GachaRoute.Waiting.route) {


        composableFadeAnim(
            GachaRoute.Waiting,
            navController = navHostController
        ) { navController, _ ->

            LaunchedEffect(key1 = true) {
                gachaInProgress(false)
            }

            WaitingScreen(
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
                }
            )
        }

        composableFadeAnim(
            GachaRoute.Start,
            navController = navHostController
        ) { navController, _ ->

            StartScreen { gachaRoute ->
                navController.toGachaDest(gachaRoute)
            }
        }

        composableFadeAnim(
            GachaRoute.Idle,
            navController = navHostController
        ) { navController, _ ->

            IdleScreen { gachaRoute ->
                navController.toGachaDest(gachaRoute)
            }
        }

        composableFadeAnim(
            GachaRoute.IdleEnd,
            navController = navHostController
        ) { navController, _ ->

           IdleEndScreen { gachaRoute ->
               navController.toGachaDest(gachaRoute)
           }
        }

        composableFadeAnim(
            route = GachaRoute.Reveal(0),
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
            route = GachaRoute.RevealIdle(0),
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
                }
            )
        }

        composableFadeAnim(
            route = GachaRoute.End,
            navController = navHostController,
        ) { navController, _ ->
            Surface(
                Modifier
                    .fillMaxSize()
                    .clickable(remember { MutableInteractionSource() }, null) {
                        navController.toGachaDest(GachaRoute.Waiting)
                    }
            ) {
                Column {
                    for (cookie in state.pull.result) {
                        Text(text = cookie.toString())
                    }
                }
            }
        }
    }
}