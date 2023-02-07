package io.silv.crcsim.feat_gacha.usecases

import io.silv.crcsim.data.datastore.UserDataStore
import io.silv.crcsim.data.room.CookieDao
import io.silv.crcsim.feat_gacha.CookieDrawResult
import io.silv.crcsim.feat_gacha.Pity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class DrawCookiesUseCase(
    private val cookieDao: CookieDao,
    private val gacha: CookieGachaSim,
    private val userDataStore: UserDataStore,
) {

    suspend operator fun invoke(pity: Pity, amount: Int): CookieDrawResult {
        return gacha.drawCookies(pity, amount)
            .also { cookieResList ->
                coroutineScope {
                    launch {
                        userDataStore.addCrystals(amount * 300)
                        userDataStore.editPity(cookieResList.newPity)
                    }
                    cookieResList.result.forEach { cookieRes ->
                        cookieDao.getCookieByName(cookieRes.cookie.name)?.let { cookieEntity ->
                            cookieDao.updateCookie(
                                cookieEntity.copy(
                                    soulstoneCount = cookieEntity.soulstoneCount + cookieRes.count
                                )
                            )
                        }
                }
            }
        }
    }
}

