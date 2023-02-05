package io.silv.crcsim.feat_gacha.usecases

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import io.silv.crcsim.R
import io.silv.crcsim.di.CrkDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PlayIdleUseAnimation(
    private val ctx: Context,
    private val dispatcher: CrkDispatcher
) {


    suspend operator fun invoke(
        exoPlayer: ExoPlayer,
        start: Boolean,
    ) = withContext(dispatcher.main) {
        with(exoPlayer) {
            if (start) {
                repeatMode = Player.REPEAT_MODE_ONE
                setMediaItem(
                    MediaItem.fromUri(
                        Uri.parse(
                            "android.resource://" +
                                    ctx.packageName + "/" +
                                    R.raw.use_idle_start
                        )
                    )
                )
            } else {
                repeatMode = Player.REPEAT_MODE_OFF
                setMediaItem(
                    MediaItem.fromUri(
                        Uri.parse(
                            "android.resource://" +
                                    ctx.packageName + "/" +
                                    R.raw.use_idle_end
                        )
                    )
                )
            }
            prepare()
            play()
        }
        while (true) {
            delay(30)
            if (exoPlayer.contentPosition >= exoPlayer.contentDuration) {
                return@withContext
            }
        }
    }
}