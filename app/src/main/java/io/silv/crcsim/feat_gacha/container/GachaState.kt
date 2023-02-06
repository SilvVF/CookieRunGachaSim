package io.silv.crcsim.feat_gacha.container

import io.silv.crcsim.feat_gacha.usecases.CookieDrawResult
import io.silv.crcsim.feat_gacha.usecases.Pity
import java.time.LocalDateTime

data class GachaState(
    val pull: CookieDrawResult = CookieDrawResult(Pity(), emptyList(), LocalDateTime.now()),
    val pity: Pity = Pity()
)