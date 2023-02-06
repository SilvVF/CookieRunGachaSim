@file:OptIn(ExperimentalAnimationApi::class)

package io.silv.crcsim.feat_gacha

import Draw10Button
import Draw1Button
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import io.silv.crcsim.feat_gacha.compose.GachaMediaPlayer
import io.silv.crcsim.feat_gacha.compose.RevealScreen
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
            is GachaPhase.Waiting ->  {
                Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround) {
                    Draw10Button(onClick = { viewModel.startCookieGacha(10) })
                    Draw1Button(onClick = { viewModel.startCookieGacha(1)})
                }
            }
            is GachaPhase.StartAnimation ->  {
                Box(
                    Modifier
                        .fillMaxSize()
                        .clickable { viewModel.skipStartAnimation() }) {
                    GachaMediaPlayer(
                        modifier = Modifier.fillMaxSize(),
                        exoPlayer = state.player
                    )
                }
            }
            is GachaPhase.Reveal, GachaPhase.RevealAnimation ->  {
                RevealScreen(
                    exoPlayer = state.player,
                    playing = remember(state.phase) {
                        derivedStateOf { state.phase == GachaPhase.RevealAnimation }.value
                    },
                    cookieDraw = state.pull.result[state.revealIdx],
                    skipRevealAnimation = {
                       viewModel.skipRevealAnimation()
                    },
                    revealNextItem = {
                        viewModel.revealNext(state.revealIdx + 1, state.pull)
                    }
                )
            }
            is GachaPhase.Started -> {
                LaunchedEffect(key1 = true) {
                    viewModel.playIdleAnim()
                }
                Box(
                    Modifier
                        .fillMaxSize()
                        .clickable {
                            viewModel.startRevealPhase()
                        }
                ) {
                    GachaMediaPlayer(exoPlayer = state.player, modifier = Modifier.fillMaxSize())
                }
            }
            is GachaPhase.End -> {
                Box(
                    Modifier
                        .fillMaxSize()
                        .clickable { viewModel.goToWaiting() }) {
                    Text("End")
                }
            }
        }
    }
}