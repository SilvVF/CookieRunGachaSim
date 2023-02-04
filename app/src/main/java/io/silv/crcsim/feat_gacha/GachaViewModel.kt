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
import io.silv.crcsim.feat_gacha.usecases.PlayGachaRevealAnimation
import io.silv.crcsim.feat_gacha.usecases.PlayGachaStartAnimation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.context.startKoin
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDateTime

class GachaViewModel(
    private val cookieDao: CookieDao,
    private val exoPlayer: ExoPlayer,
    private val draw10: Draw10UseCase,
    private val playGachaStartAnimation: PlayGachaStartAnimation,
    private val playGachaRevealAnimation: PlayGachaRevealAnimation
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
            state.copy(
                phase = GachaPhase.StartAnimation,
                pull = pull,
                revealIdx = 0,
            )
        }
        playGachaStartAnimation(exoPlayer)
        reduce {
            state.copy(
                phase = GachaPhase.Started,
                pull = pull,
                revealIdx = 0,
            )
        }
    }

    fun goToWaiting() = intent {
        reduce {
            state.copy(
                phase = GachaPhase.Waiting
            )
        }
    }

    fun skipStartAnimation() = intent {
        clearMediaItems()
        reduce {
            state.copy(
                phase = GachaPhase.Started
            )
        }
    }
    fun skipRevealAnimation() = intent {
        clearMediaItems()
        reduce {
            state.copy(
                phase = GachaPhase.Reveal
            )
        }
    }

    private suspend fun clearMediaItems() = withContext(Dispatchers.Main) {
        exoPlayer.clearMediaItems()
    }
    fun revealNext(nextIdx: Int, pull: CookieDrawResult) = intent {
        if (nextIdx >= pull.result.lastIndex) {
            reduce {
                state.copy(phase = GachaPhase.End, revealIdx = 0)
            }
            return@intent
        }
        reduce { state.copy(revealIdx = nextIdx,) }
        playGachaRevealAnimation(
            exoPlayer = exoPlayer,
            cookieDraw = pull.result[nextIdx],
            onStart = {
                reduce {
                    state.copy(
                        phase = GachaPhase.RevealAnimation
                    )
                }
            }
        )
        reduce {
            state.copy(
                phase = GachaPhase.Reveal
            )
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
    val total: Int = 0,
    val pull: CookieDrawResult = CookieDrawResult(Pity(), result = emptyList(), LocalDateTime.now()),
    val revealIdx: Int = 0,
)

sealed interface GachaPhase {
    /**
     * Object Representing the phase before the button to start a gacha pull is clicked
     */
    object Waiting: GachaPhase

    /**
     * This represents the part from the witches house to the initial reveal of the cookies obtained
     */
    object StartAnimation: GachaPhase
    object Started: GachaPhase

    object RevealAnimation: GachaPhase
    object Reveal: GachaPhase

    object End: GachaPhase
}

sealed interface GachaEffect {

}
