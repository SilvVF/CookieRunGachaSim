package io.silv.crcsim.feat_gacha.usecases

import io.silv.crcsim.data.datastore.UserDataStore
import io.silv.crcsim.data.room.CookieDao
import kotlinx.coroutines.coroutineScope

class DrawCookiesUseCase(
    private val cookieDao: CookieDao,
    private val gacha: CookieGachaSim,
    private val userDataStore: UserDataStore,
) {

    suspend operator fun invoke(pity: Pity, amount: Int): CookieDrawResult {
        coroutineScope {
            userDataStore.addCrystals(amount * 300)
        }
        return gacha.drawCookies(pity, amount)
            .also { cookieResList ->
                cookieResList.result.forEach { cookieRes ->
                    cookieDao.getCookieByName(cookieRes.cookie.name)?.let {
                        cookieDao.updateCookie(
                            it.copy(soulstoneCount = it.soulstoneCount + cookieRes.count)
                        )
                }
            }
        }
    }
}

