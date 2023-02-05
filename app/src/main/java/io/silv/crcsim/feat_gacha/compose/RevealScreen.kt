package io.silv.crcsim.feat_gacha.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.with
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import io.silv.crcsim.feat_gacha.usecases.CookieDraw

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RevealScreen(
    exoPlayer: ExoPlayer,
    playing: Boolean,
    cookieDraw: CookieDraw,
    skipRevealAnimation: () -> Unit,
    revealNextItem: () -> Unit
) {

    Box(
        Modifier
            .fillMaxSize()
            .border(4.dp, Color.Magenta)
            .clickable {
                if (playing)
                    skipRevealAnimation()
                else
                    revealNextItem()
            }
    ) {
        AnimatedContent(targetState = playing, transitionSpec = { fadeIn() with ExitTransition.None }) { playing ->
            if (playing) {
                GachaMediaPlayer(
                    exoPlayer = exoPlayer,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Column {
                    Text(text = cookieDraw.toString())
                    Text("Reveal")
                }
            }
        }
    }
}