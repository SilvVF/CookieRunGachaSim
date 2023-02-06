package io.silv.crcsim.feat_gacha

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.silv.crcsim.R
import io.silv.crcsim.feat_gacha.compose.GachaRoute
import io.silv.crcsim.feat_gacha.compose.components.Player
import io.silv.crcsim.feat_gacha.compose.screens.IdleEndScreen
import io.silv.crcsim.feat_gacha.compose.screens.WaitingScreen
import io.silv.crcsim.feat_gacha.compose.screens.IdleScreen
import io.silv.crcsim.feat_gacha.compose.screens.StartScreen
import io.silv.crcsim.navigation.composableFadeAnim
import io.silv.crcsim.navigation.toGachaDest
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GachaNavHost(
    viewModel: GachaViewModel = koinViewModel()
) {

    val navHostController = rememberAnimatedNavController()
    val state by viewModel.collectAsState()


    AnimatedNavHost(navController = navHostController, startDestination = GachaRoute.Waiting.route) {

        composableFadeAnim(
            GachaRoute.Waiting,
            navHostController
        ) { navController ->

            WaitingScreen(
                onDraw1Click = {
                    viewModel.handleDraw1Click()
                    navController.toGachaDest(GachaRoute.Start)
                },
                onDraw10Click = {
                    viewModel.handleDraw10Click()
                    navController.toGachaDest(GachaRoute.Start)
                }
            )
        }

        composableFadeAnim(
            GachaRoute.Start,
            navController = navHostController
        ) { navController ->

            StartScreen { gachaRoute ->
                navController.toGachaDest(gachaRoute)
            }
        }

        composableFadeAnim(
            GachaRoute.Idle,
            navController = navHostController
        ) { navController ->

            IdleScreen { gachaRoute ->
                navController.toGachaDest(gachaRoute)
            }
        }

        composableFadeAnim(
            GachaRoute.IdleEnd,
            navController = navHostController
        ) { navController ->

           IdleEndScreen { gachaRoute ->
               navController.toGachaDest(gachaRoute)
           }
        }
    }
}