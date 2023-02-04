package io.silv.crcsim.feat_gacha.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            .clickable {
                if (playing)
                    skipRevealAnimation()
                else
                    revealNextItem()
            }
    ) {
        AnimatedContent(targetState = playing, transitionSpec = { fadeIn() with fadeOut() }) { playing ->
            if (playing) {
                GachaMediaPlayer(exoPlayer = exoPlayer)
            } else {
                Text(text = cookieDraw.toString())
            }
        }
    }
}