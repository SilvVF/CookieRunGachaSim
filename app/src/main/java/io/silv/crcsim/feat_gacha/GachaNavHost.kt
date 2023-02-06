package io.silv.crcsim.feat_gacha

import android.net.Uri
import android.provider.MediaStore.Audio.Media
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.google.accompanist.navigation.animation.AnimatedNavHost import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import io.silv.crcsim.R
import io.silv.crcsim.feat_gacha.compose.MediaScreen
import io.silv.crcsim.feat_gacha.compose.Player
import io.silv.crcsim.feat_gacha.compose.WaitingScreen
import io.silv.crcsim.navigation.composableFadeAnims
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.startKoin
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GachaNavHost(
    viewModel: GachaViewModelNew = koinViewModel()
) {

    val navHostController = rememberAnimatedNavController()
    val ctx = LocalContext.current
    val state by viewModel.collectAsState()

    var currentMillis by remember {
        mutableStateOf(0L)
    }

    AnimatedNavHost(navController = navHostController, startDestination = "waiting") {

        composableFadeAnims("waiting", navHostController) { navController ->
            WaitingScreen(
                onDraw1Click = {
                    viewModel.performGachaPull(1)
                    navController.navigate("start")
                },
                onDraw10Click = {
                    viewModel.performGachaPull(10)
                    navController.navigate("start")
                }
            )
        }

        composableFadeAnims("start", navController = navHostController) { navController ->

            Player(
                mediaItem = MediaItem.fromUri(
                    Uri.parse(
                        "android.resource://" +
                                ctx.packageName + "/" +
                                R.raw.intro_single_epic
                    )
                ),
                contentPosition = {currentMillis = it}
            ) {
                navController.navigate("idle")
            }
            Text(
                remember(currentMillis) {
                    derivedStateOf { currentMillis.toString() }.value
                }
            )
        }

        composableFadeAnims("idle", navController = navHostController) { navController ->

            Player(
                mediaItem = MediaItem.fromUri(
                    Uri.parse(
                        "android.resource://" +
                                ctx.packageName + "/" +
                                R.raw.use_idle_start
                    )
                ),
                contentPosition = {currentMillis = it}
            ){
                navController.navigate("idle-end")
            }
            Text("$currentMillis")
        }

        composableFadeAnims("idle-end", navController = navHostController) {
            Player(
                mediaItem = MediaItem.fromUri(
                    Uri.parse(
                        "android.resource://" +
                                ctx.packageName + "/" +
                                R.raw.use_idle_end
                    )
                ),
                contentPosition = {currentMillis = it}
            ){
                it.navigate("waiting")
            }
            Text("$currentMillis")
        }
    }
}