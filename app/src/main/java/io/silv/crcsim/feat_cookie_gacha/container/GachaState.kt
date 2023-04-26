package io.silv.crcsim.feat_cookie_gacha.container


import io.silv.crcsim.feat_cookie_gacha.CookieDrawResult
import io.silv.crcsim.feat_cookie_gacha.Pity
import java.time.LocalDateTime

data class GachaState(
    val pull: CookieDrawResult = CookieDrawResult(Pity(), emptyList(), LocalDateTime.now()),
    val pity: Pity = Pity(),
    val crystalsSpent: Int = 0,
)

sealed interface HistoryFilter {
    object None: HistoryFilter
    object Cookie: HistoryFilter
    object Artifact: HistoryFilter
}