package io.silv.crcsim.feat_gacha.compose

import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun GachaMediaPlayer(
    modifier: Modifier = Modifier,
    playing: Boolean,
    uri: Uri,
    onCurrentPositionChange: (positionMillis: Long) -> Unit
) {

    val ctx = LocalContext.current

    val exoPlayer: ExoPlayer = remember {
        ExoPlayer.Builder(ctx)
            .build()
            .apply {
                setMediaItem(MediaItem.fromUri(uri))
            }
    }

    LaunchedEffect(key1 = true) {
        exoPlayer.prepare()
    }

    LaunchedEffect(key1 = exoPlayer.currentPosition) {
        onCurrentPositionChange(exoPlayer.currentPosition)
    }


    LaunchedEffect(playing) {
        if (playing) {
            exoPlayer.play()
        } else {
            exoPlayer.pause()
        }
    }

    DisposableEffect(
        AndroidView(
            modifier = modifier,
            factory = {
                PlayerView(it).apply {
                    player = exoPlayer
                    useController = false
                    layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                }
            },
            update = {}
        )
    ) {
        onDispose { exoPlayer.release() }
    }
}