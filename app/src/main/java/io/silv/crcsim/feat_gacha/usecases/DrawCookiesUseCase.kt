package io.silv.crcsim.feat_gacha.usecases

import io.silv.crcsim.data.datastore.UserDataStore
import io.silv.crcsim.data.room.CookieDao
import io.silv.crcsim.feat_gacha.CookieDraw
import io.silv.crcsim.feat_gacha.CookieDrawResult
import io.silv.crcsim.feat_gacha.Pity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

fun interface DrawCookiesUseCase : suspend (Pity, Int) -> CookieDrawResult

suspend fun drawCookiesUseCaseImpl(
    cookieDao: CookieDao,
    gacha: CookieGachaSim,
    userDataStore: UserDataStore,
    pity: Pity,
    amount: Int,
): CookieDrawResult {
    return gacha.drawCookies(pity, amount)
        .also { cookieDrawResult ->
            coroutineScope {
                launch {
                    userDataStore.addCrystals(amount * 300)
                    userDataStore.editPity(cookieDrawResult.newPity)
                }
                launch {
                    updateCrystalCount(cookieDao, cookieDrawResult)
                }
            }
        }
}

private suspend fun updateCrystalCount(cookieDao: CookieDao, cookieDrawResult: CookieDrawResult) {
    cookieDrawResult.result.forEach { draw ->
        with(cookieDao) {
            getCookieByName(draw.cookie.name)?.let { cookieEntity ->
            updateCookie(
                cookieEntity.copy(
                    soulstoneCount = cookieEntity.soulstoneCount + draw.count
                )
            )}
        }
    }
}

