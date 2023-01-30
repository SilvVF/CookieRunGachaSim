package io.silv.crcsim.feat_gacha

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import io.silv.crcsim.data.allCookies
import io.silv.crcsim.data.room.CookieDao
import io.silv.crcsim.feat_gacha.usecases.CookieDraw
import io.silv.crcsim.feat_gacha.usecases.CookieDrawResult
import io.silv.crcsim.feat_gacha.usecases.Draw10UseCase
import io.silv.crcsim.feat_gacha.usecases.Pity
import io.silv.crcsim.feat_gacha.usecases.PlayGachaRevealAnimations
import io.silv.crcsim.feat_gacha.usecases.PlayGachaStartAnimation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class GachaViewModel(
    private val cookieDao: CookieDao,
    private val exoPlayer: ExoPlayer,
    private val draw10: Draw10UseCase,
    private val playGachaStartAnimation: PlayGachaStartAnimation,
    private val playGachaRevealAnimations: PlayGachaRevealAnimations
) : ViewModel(), ContainerHost<GachaState, GachaEffect> {

    override val container = container<GachaState, GachaEffect>(
        initialState = GachaState(exoPlayer)
    ) {
        init()
    }

    private fun init() = intent {
        test()
    }

    fun draw10Cookies() = intent {
        val pull = draw10(Pity())
        reduce {
            state.copy(phase = GachaPhase.Started)
        }
        playGachaStartAnimation(exoPlayer)
        playGachaRevealAnimations(exoPlayer, pull)
        reduce {
            state.copy(phase = GachaPhase.Waiting)
        }
    }
    

    private fun test() = viewModelScope.launch {
        cookieDao.allCookiesFlow().first()?.let { list ->
            assert(
                allCookies().none { it.id !in list.map { cookie -> cookie.name } }
            )
        }
    }

    override fun onCleared() {
        CoroutineScope(Dispatchers.Main).launch {
            exoPlayer.release()
        }
        super.onCleared()
    }
}

data class GachaState(
    val player: ExoPlayer,
    val phase: GachaPhase = GachaPhase.Waiting,
    val cookies: List<CookieDraw> = emptyList(),
    val total: Int = 0
)

sealed class GachaPhase {
    /**
     * Object Representing the phase before the button to start a gacha pull is clicked
     */
    object Waiting: GachaPhase()

    /**
     * This represents the part from the witches house to the initial reveal of the cookies obtained
     */
    object Started: GachaPhase()

    /**
     * Data class representing the phase where the cookies come out of the over and are revealed.
     * @property pull contains the current pull result [CookieDrawResult] that will be revealed.
     */
    data class Reveal(val pull: CookieDrawResult): GachaPhase()
}

sealed interface GachaEffect {

}
