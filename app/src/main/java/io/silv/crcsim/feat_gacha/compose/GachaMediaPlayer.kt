@file:androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)

package io.silv.crcsim.feat_gacha.compose

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.AspectRatioFrameLayout.ResizeMode
import androidx.media3.ui.PlayerView

@Composable
fun GachaMediaPlayer(
    modifier: Modifier = Modifier,
    exoPlayer: ExoPlayer
) {
    AndroidView(
        modifier = modifier,
        factory = {
            PlayerView(it).apply {
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                player = exoPlayer
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            }
        },
    )
}