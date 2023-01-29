package io.silv.crcsim.feat_gacha.usecases

import io.silv.crcsim.data.datastore.UserDataStore
import io.silv.crcsim.data.room.CookieDao
import kotlinx.coroutines.coroutineScope

class Draw10UseCase(
    private val cookieDao: CookieDao,
    private val gacha: CookieGachaSim,
    private val userDataStore: UserDataStore
) {

    suspend operator fun invoke(pity: Pity): CookieDrawResult {
        coroutineScope {
            userDataStore.addCrystals(3000)
        }
        return gacha.draw10Cookies(pity)
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