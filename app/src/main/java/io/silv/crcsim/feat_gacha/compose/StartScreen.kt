@file:androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)

package io.silv.crcsim.feat_gacha.compose

import android.net.Uri
import android.os.Parcelable
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import io.silv.crcsim.R

@Composable
fun  MediaScreen(
    exoPlayer: ExoPlayer,
    mediaItem: MediaItem,
    onNavigate: () -> Unit,
) {

    val ctx = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(true) {
        if (exoPlayer.mediaItemCount == 0)
            exoPlayer.setMediaItem(
                mediaItem
            )
        exoPlayer.prepare()
        exoPlayer.play()
    }





    DisposableEffect(
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onNavigate() },
            factory = { context ->
                PlayerView(context).apply {
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
        )
    ) {
        onDispose {
            exoPlayer.pause()
        }
    }
}