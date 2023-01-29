package io.silv.crcsim.feat_gacha.usecases

import io.silv.crcsim.data.Cookie
import io.silv.crcsim.data.allCookies
import io.silv.crcsim.data.filterRarity
import kotlin.random.Random

class CookieGachaSim {

    private fun getCookie(rarity: Rarity): CookieDraw {
        val cookie =  allCookies().filterRarity(rarity)
            .ifEmpty { throw IllegalStateException("Cookie List Was Empty for rarity $rarity") }
            .random(
                random = Random(System.currentTimeMillis())
            )
        return CookieDraw(cookie, true, 20)
    }

    private fun getSoulStones(rarity: Rarity): CookieDraw {
        val cookie = allCookies().filterRarity(rarity)
            .ifEmpty { throw IllegalStateException("Soulstone List Was Empty for rarity $rarity") }
            .random(
                random = Random(System.currentTimeMillis())
            )
        return CookieDraw(cookie, false, Random.nextInt(3, 6))
    }

    private fun randomDraw(): CookieDraw {
        return when (Random.nextDouble(0.0, combinedRates)) {
            in commonRange -> { getCookie(Rarity.Common) }
            in rareRange -> { getCookie(Rarity.Rare) }
            in epicRange -> { getCookie(Rarity.Epic) }
            in superEpicRange -> { getCookie(Rarity.Special) }
            in legendaryRange -> { getCookie(Rarity.Legendary) }
            in commonStoneRange -> { getSoulStones(Rarity.Common) }
            in rareStoneRange -> { getSoulStones(Rarity.Rare) }
            in epicStoneRange -> { getSoulStones(Rarity.Epic) }
            in superEpicStoneRange -> { getSoulStones(Rarity.Special) }
            else -> { getSoulStones(Rarity.Legendary) }
        }
    }

    private fun anyPityDraw(): CookieDraw {
        return when (Random.nextDouble(0.0, cookieRates.sum())) {
            in commonRange -> {  getCookie(Rarity.Common) }
            in rareRange -> { getCookie(Rarity.Rare) }
            in epicRange -> { getCookie(Rarity.Epic) }
            in superEpicRange -> { getCookie(Rarity.Special) }
            else -> { getCookie(Rarity.Legendary) }
        }
    }

    private fun epicPityDraw(): CookieDraw {
        return when(Random.nextDouble(epicRange.start, legendaryRange.endInclusive)) {
            in epicRange -> { getCookie(Rarity.Epic) }
            in superEpicRange -> { getCookie(Rarity.Special) }
            else -> { getCookie(Rarity.Legendary) }
        }
    }

    fun draw10Cookies(pity: Pity): CookieDrawResult {

        var newPity = pity.copy()

        val draw10 = buildList<CookieDraw> {
            repeat(10) {
                add(
                    when {
                        newPity.any >= 10 -> anyPityDraw().also { newPity = newPity.update(it) }
                        newPity.epic >= 100 -> anyPityDraw().also { newPity = newPity.update(it) }
                        newPity.other >= 250 -> getCookie(Rarity.Legendary).also { newPity = newPity.update(it) }
                        else ->  randomDraw().also { newPity = newPity.update(it) }
                    }
                )
            }
        }
        return CookieDrawResult(newPity, draw10)
    }

    private companion object {
        const val CommonRate         = 9.555
        const val RareRate           = 5.236
        const val EpicRate           = 2.808
        const val SuperEpicRate      = 0.147
        const val LegendaryRate      = 0.162
        const val CommonStoneRate    = 31.878
        const val RareStoneRate      = 31.878
        const val EpicStoneRate      = 15.860
        const val SuperEpicStoneRate = 0.843
        const val LegendaryStoneRate = 0.924

        val stoneRates = listOf(
            CommonStoneRate,
            RareStoneRate,
            EpicStoneRate,
            SuperEpicStoneRate,
            LegendaryStoneRate
        )
        val cookieRates = listOf(
            CommonRate,
            RareRate,
            EpicRate,
            SuperEpicRate,
            LegendaryRate,
        )

        val combinedRates = stoneRates.sum() + cookieRates.sum()

        val commonRange = 0.0..(CommonRate)
        val rareRange = commonRange.endInclusive..(commonRange.endInclusive + RareRate)
        val epicRange = rareRange.endInclusive..(rareRange.endInclusive + EpicRate)
        val superEpicRange = epicRange.endInclusive..(epicRange.endInclusive + SuperEpicRate)
        val legendaryRange = superEpicRange.endInclusive..(superEpicRange.endInclusive + LegendaryRate)

        val commonStoneRange = legendaryRange.endInclusive..(legendaryRange.endInclusive + CommonStoneRate)
        val rareStoneRange = commonStoneRange.endInclusive..(commonStoneRange.endInclusive + RareStoneRate)
        val epicStoneRange = rareStoneRange.endInclusive..(rareStoneRange.endInclusive + EpicStoneRate)
        val superEpicStoneRange = epicStoneRange.endInclusive..(epicStoneRange.endInclusive + SuperEpicStoneRate)
        val legendaryStoneRange = superEpicStoneRange.endInclusive..(superEpicStoneRange.endInclusive + LegendaryStoneRate)
    }
}

data class CookieDrawResult(
    val newPity: Pity,
    val result: List<CookieDraw>
)

data class Pity(
    val any: Int = 0,
    val epic: Int = 0,
    val other: Int = 0
)

fun Pity.update(cookieDraw: CookieDraw): Pity {
    return when (cookieDraw.full) {
        true -> this
        else -> {
            when (cookieDraw.cookie.rarity) {
                "c" -> this.copy()
                in "s", "e" -> this.copy(any = 0, epic = 0)
                "l" -> this.copy(any = 0, epic = 0, other = 0)
                else -> this.copy()
            }
        }
    }

}

sealed interface Rarity {
    object Common: Rarity
    object Rare: Rarity
    object Epic: Rarity
    object Special: Rarity
    object Legendary: Rarity
}

fun Rarity.string(): String {
    return when (this) {
        Rarity.Rare -> "r"
        Rarity.Epic -> "e"
        Rarity.Special -> "s"
        Rarity.Legendary -> "l"
        else -> "c"
    }
}

fun Rarity.from(s: String): Rarity {
    return when (s) {
        "r" -> Rarity.Rare
        "e" -> Rarity.Epic
        "s" -> Rarity.Special
        "l" -> Rarity.Legendary
        else -> Rarity.Common
    }
}

data class CookieDraw(
    val cookie: Cookie,
    val full: Boolean,
    val count: Int
)