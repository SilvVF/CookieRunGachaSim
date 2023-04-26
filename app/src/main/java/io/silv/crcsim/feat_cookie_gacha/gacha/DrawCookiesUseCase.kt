package io.silv.crcsim.feat_cookie_gacha.gacha

import io.silv.crcsim.data.datastore.UserDataStore
import io.silv.crcsim.data.room.dao.CookieDao
import io.silv.crcsim.data.room.dao.PullHistDao
import io.silv.crcsim.data.room.entity.PullHistEntity
import io.silv.crcsim.feat_cookie_gacha.CookieDrawResult
import io.silv.crcsim.feat_cookie_gacha.Pity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

fun interface DrawCookiesUseCase : suspend (Pity, Int) -> CookieDrawResult

suspend fun drawCookiesUseCaseImpl(
    cookieDao: CookieDao,
    pullHistDao: PullHistDao,
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
                    updateDb(cookieDao, pullHistDao, cookieDrawResult)
                }
            }
        }
}

private suspend fun updateDb(cookieDao: CookieDao, pullHistDao: PullHistDao, cookieDrawResult: CookieDrawResult) {
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
    pullHistDao.addHistoryItem(
        PullHistEntity(
            type = "cookie",
            items = cookieDrawResult.result.map { it.cookie.name },
            amounts = cookieDrawResult.result.map { it.count }
        )
    )
}

