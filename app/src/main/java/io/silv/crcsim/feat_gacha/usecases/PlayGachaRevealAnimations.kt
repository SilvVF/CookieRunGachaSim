package io.silv.crcsim.feat_gacha.usecases

import android.content.Context
import android.net.Uri
import androidx.annotation.RawRes
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import io.silv.crcsim.R
import io.silv.crcsim.di.CrkDispatcher
import io.silv.crcsim.models.Rarity
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PlayGachaRevealAnimations(
    private val dispatcher: CrkDispatcher,
    private val ctx: Context,
) {

    suspend operator fun invoke(
        exoPlayer: ExoPlayer,
        cookieDrawResult: CookieDrawResult,
    ) = withContext(dispatcher.main) {


        exoPlayer.setMediaItems(
            buildList {
                cookieDrawResult.result.forEach { cookieDraw ->
                    if (cookieDraw.full) {
                        add(
                            MediaItem.fromUri(getVideoUri(cookieDraw.cookie.rarity))
                        )
                    }
                }
            }
        )
        exoPlayer.prepare()
        exoPlayer.play()

        while (true) {
            delay(100)
            if (exoPlayer.contentPosition >= exoPlayer.contentDuration) {
                return@withContext
            }
        }
    }

    private fun getVideoUri(rarity: Rarity) =
        when(rarity) {
            Rarity.Common-> buildUri(R.raw.common_oven)
            Rarity.Rare -> buildUri(R.raw.rare_oven)
            Rarity.Epic, Rarity.Special, Rarity.Legendary -> buildUri(R.raw.epic_oven)
        }

    private fun buildUri(
        @RawRes resId: Int
    ): Uri {
        return Uri.parse(
            "android.resource://" +
                    ctx.packageName + "/" +
                    resId
        )
    }
}