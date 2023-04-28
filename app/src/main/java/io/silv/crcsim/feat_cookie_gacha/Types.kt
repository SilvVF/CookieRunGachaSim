package io.silv.crcsim.feat_cookie_gacha

import io.silv.crcsim.data.Cookie
import io.silv.crcsim.models.Rarity
import java.time.LocalDateTime

sealed interface GachaEffect
data class CookieDrawResult(
    val newPity: Pity,
    val result: List<CookieDraw>,
    val time: LocalDateTime
)

data class Pity(
    val any: Int = 0,
    val epic: Int = 0,
    val other: Int = 0
)

data class CookieDraw(
    val cookie: Cookie,
    val full: Boolean,
    val count: Int
)

data class GachaState(
    val pull: CookieDrawResult = CookieDrawResult(Pity(), emptyList(), LocalDateTime.now()),
    val pity: Pity = Pity(),
    val crystalsSpent: Int = 0,
)

sealed interface HistoryFilter {
    object None: HistoryFilter
    object Cookie: HistoryFilter
    object Treasure: HistoryFilter
}

fun Pity.update(cookieDraw: CookieDraw): Pity =
    when (cookieDraw.full) {
        false -> Pity(
            any = this.any + 1,
            epic = this.epic + 1,
            other = this.other + 1
        )
        else -> {
            when (cookieDraw.cookie.rarity) {
                Rarity.Common, Rarity.Rare -> this.copy(any = 0)
                Rarity.Epic, Rarity.Special -> this.copy(any = 0, epic = 0)
                Rarity.Ancient, Rarity.Legendary-> this.copy(any = 0, epic = 0, other = 0)
            }
        }
    }
