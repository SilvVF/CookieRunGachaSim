package io.silv.crcsim.feat_gacha.usecases

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import io.silv.crcsim.R
import io.silv.crcsim.di.CrkDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PlayGachaStartAnimation(
    private val dispatcher: CrkDispatcher,
    private val ctx: Context,
) {

    suspend operator fun invoke(
        exoPlayer: ExoPlayer,
    ) = withContext(dispatcher.main) {

        val uri = Uri.parse(
            "android.resource://" +
                    ctx.packageName + "/" +
                    R.raw.intro_single_epic
        )
        exoPlayer.clearMediaItems()
        exoPlayer.setMediaItem(MediaItem.fromUri(uri))
        exoPlayer.prepare()
        exoPlayer.play()

        while (true) {
            delay(30)
            if (exoPlayer.contentPosition >= exoPlayer.contentDuration) {
                return@withContext
            }
        }
    }
}