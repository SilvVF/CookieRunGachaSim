package io.silv.crcsim.feat_gacha.container

import androidx.media3.exoplayer.ExoPlayer
import io.silv.crcsim.feat_gacha.GachaPhase
import io.silv.crcsim.feat_gacha.usecases.CookieDraw
import io.silv.crcsim.feat_gacha.usecases.CookieDrawResult
import io.silv.crcsim.feat_gacha.usecases.Pity
import java.time.LocalDateTime

data class GachaState(
    val player: ExoPlayer,
    val phase: GachaPhase = GachaPhase.Waiting,
    val cookies: List<CookieDraw> = emptyList(),
    val total: Int = 0,
    val pull: CookieDrawResult = CookieDrawResult(Pity(), result = emptyList(), LocalDateTime.now()),
    val revealIdx: Int = 0,
)
