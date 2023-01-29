package io.silv.crcsim.feat_gacha

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.silv.crcsim.data.allCookies
import io.silv.crcsim.data.room.CookieDao
import io.silv.crcsim.feat_gacha.usecases.CookieDraw
import io.silv.crcsim.feat_gacha.usecases.Draw10UseCase
import io.silv.crcsim.feat_gacha.usecases.Pity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.syntax.simple.repeatOnSubscription
import org.orbitmvi.orbit.viewmodel.container

class GachaViewModel(
    private val cookieDao: CookieDao,
    private val draw10: Draw10UseCase,
) : ViewModel(), ContainerHost<GachaState, GachaEffect> {

    override val container = container<GachaState, GachaEffect>(
        initialState = GachaState()
    ) {
        init()
    }

    private fun init() = intent {
        test()
        repeatOnSubscription {
            cookieDao.allCookiesFlow().collect { cookies ->
                reduce {
                    state.copy(
                        db = cookies?.map { it.toString() } ?: emptyList(),
                        total = cookies?.sumOf {
                            it.soulstoneCount
                        } ?: 0
                    )
                }
            }
        }
    }

    fun draw10Cookies() = intent {
        val result = draw10(pity = Pity()).result
        reduce {
            state.copy(
                cookies = result
            )
        }
    }

    private fun test() = viewModelScope.launch {
        cookieDao.allCookiesFlow().first()?.let { list ->
            assert(allCookies().none { it.id !in list.map { cookie -> cookie.name } })
        }
    }
}

data class GachaState(
    val db : List<String> = emptyList(),
    val cookies: List<CookieDraw> = emptyList(),
    val total: Int = 0
)

sealed interface GachaEffect {

}
