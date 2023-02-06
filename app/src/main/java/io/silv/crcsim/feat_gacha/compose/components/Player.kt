@file:androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)

package io.silv.crcsim.feat_gacha.compose.components


import android.os.Looper
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Player(
     mediaItem: MediaItem,
     contentPosition: (millis: Long) -> Unit = { _ -> },
     playerBlock: ExoPlayer.() -> Unit = {  },
     mediaEnd: () -> Unit
) {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val scope = rememberCoroutineScope()

    var autoPlay by rememberSaveable { mutableStateOf(true) }
    var window by rememberSaveable { mutableStateOf(0) }
    var position by rememberSaveable { mutableStateOf(0L) }

    val player = remember {
        ExoPlayer.Builder(context)
            .build().apply {
                playerBlock()
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = autoPlay
                seekTo(window, position)
            }
            .also { player ->
                scope.launch {
                    delay(1000)
                    player.createMessage { _, _ ->
                        mediaEnd()
                    }
                        .setLooper(Looper.getMainLooper())
                        .setPosition(
                            /* mediaItemIndex= */0,
                            /* positionMs= */player.contentDuration
                        )
                        .setDeleteAfterDelivery(true)
                        .send()
                }
            }
    }

    LaunchedEffect(key1 = true) {
        while (true) {
            contentPosition(player.currentPosition)
            delay(10)
        }
    }

    fun updateState() {
        autoPlay = player.playWhenReady
        window = player.currentMediaItemIndex
        position = 0L.coerceAtLeast(player.contentPosition)
    }

    val playerView = remember {
        val playerView = PlayerView(context).apply {
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when(event) {
                    Lifecycle.Event.ON_START -> {
                        playerView.onResume()
                        player.playWhenReady = autoPlay
                    }
                    Lifecycle.Event.ON_STOP -> {
                        updateState()
                        playerView.onPause()
                        player.playWhenReady = true
                    }
                    else -> Unit
                }
            }
        })
        playerView
    }
    DisposableEffect(
        AndroidView(
            factory = { playerView },
            modifier = Modifier
                .fillMaxWidth()
        ) { _ ->
            playerView.player = player
        }
    ) {
        onDispose {
            updateState()
            player.release()
        }
    }
}