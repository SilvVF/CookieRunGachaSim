package io.silv.crcsim.feat_gacha.container


import io.silv.crcsim.feat_gacha.CookieDrawResult
import io.silv.crcsim.feat_gacha.Pity
import java.time.LocalDateTime

data class GachaState(
    val pull: CookieDrawResult = CookieDrawResult(Pity(), emptyList(), LocalDateTime.now()),
    val pity: Pity = Pity()
)