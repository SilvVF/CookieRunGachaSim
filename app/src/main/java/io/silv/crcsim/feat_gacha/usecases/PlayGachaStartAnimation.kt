package io.silv.crcsim.feat_gacha.usecases

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import io.silv.crcsim.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PlayGachaStartAnimation(
    private val ctx: Context,
) {

    suspend operator fun invoke(
        exoPlayer: ExoPlayer,
    ) = withContext(Dispatchers.Main) {

        val uri = Uri.parse(
            "android.resource://" +
                    ctx.packageName + "/" +
                    R.raw.epic_pull
        )

        exoPlayer.setMediaItem(MediaItem.fromUri(uri))
        exoPlayer.prepare()
        exoPlayer.play()

        while (true) {
            delay(100)
            if (exoPlayer.contentPosition >= exoPlayer.contentDuration * 0.9f) {
                return@withContext
            }
        }
    }
}