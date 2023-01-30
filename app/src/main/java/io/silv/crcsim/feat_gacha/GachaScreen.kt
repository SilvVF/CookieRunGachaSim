@file:OptIn(ExperimentalAnimationApi::class)

package io.silv.crcsim.feat_gacha

import Draw10Button
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import io.silv.crcsim.feat_gacha.compose.GachaMediaPlayer
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun GachaScreen(
    navController: NavController,
    viewModel: GachaViewModel = koinViewModel()
) {

    val ctx = LocalContext.current
    val state by viewModel.collectAsState()


    AnimatedContent(
        targetState = state.phase,
        modifier = Modifier.fillMaxSize(),
        transitionSpec =  { fadeIn() with fadeOut() }
    ) {
        when(state.phase) {
            GachaPhase.Waiting ->  {
                Box(Modifier.fillMaxSize()) {
                    Draw10Button(onClick = { viewModel.draw10Cookies() })
                }
            }
            GachaPhase.Started ->  {
                Box(Modifier.fillMaxSize()) {
                    GachaMediaPlayer(
                        modifier = Modifier.fillMaxSize(),
                        exoPlayer = state.player
                    )
                }
            }
            is GachaPhase.Reveal ->  {

            }
        }
    }
}