package io.silv.crcsim.feat_gacha

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import io.silv.crcsim.data.allCookies
import io.silv.crcsim.data.room.CookieDao
import io.silv.crcsim.feat_gacha.container.GachaEffect
import io.silv.crcsim.feat_gacha.container.GachaState
import io.silv.crcsim.feat_gacha.usecases.CookieDraw
import io.silv.crcsim.feat_gacha.usecases.CookieDrawResult
import io.silv.crcsim.feat_gacha.usecases.DrawCookiesUseCase
import io.silv.crcsim.feat_gacha.usecases.Pity
import io.silv.crcsim.feat_gacha.usecases.PlayGachaRevealAnimation
import io.silv.crcsim.feat_gacha.usecases.PlayGachaStartAnimation
import io.silv.crcsim.feat_gacha.usecases.PlayIdleUseAnimation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDateTime

class GachaViewModel(
    private val cookieDao: CookieDao,
    private val exoPlayer: ExoPlayer,
    private val drawCookies: DrawCookiesUseCase,
    private val playGachaStartAnimation: PlayGachaStartAnimation,
    private val playGachaRevealAnimation: PlayGachaRevealAnimation,
    private val playGachaStartIdleUseAnimation: PlayIdleUseAnimation
) : ViewModel(), ContainerHost<GachaState, GachaEffect> {

    override val container = container<GachaState, GachaEffect>(
        initialState = GachaState(exoPlayer)
    ) {
        init()
    }
    private fun init() = intent { test() }


    fun startCookieGacha(drawAmount: Int) = viewModelScope.launch {
        intent {
            assert(
                drawAmount in listOf(1, 10),
                lazyMessage = { "drawAmount was not 1 or 10 when starting gacha" })
            val pull = drawCookies(Pity(), drawAmount)
            reduce {
                state.copy(
                    phase = GachaPhase.StartAnimation,
                    pull = pull,
                    revealIdx = 0,
                )
            }
            playGachaStartAnimation(state.player)
            reduce {
                state.copy(
                    phase = GachaPhase.Started,
                    pull = pull,
                    revealIdx = 0,
                )
            }
        }
    }

    fun startRevealPhase() = viewModelScope.launch {
        intent {
            coroutineScope {
                async { playGachaStartIdleUseAnimation(state.player, false) }.await()
                revealNext(0, state.pull)
            }
        }
    }
    fun playIdleAnim() = viewModelScope.launch {
        intent {
            playGachaStartIdleUseAnimation(state.player, true)
        }
    }

    fun goToWaiting() = intent {
        reduce {
            state.copy(
                phase = GachaPhase.Waiting
            )
        }
    }

    fun skipStartAnimation() = viewModelScope.launch {
        clearMediaItems()
        intent {
            reduce {
                state.copy(
                    phase = GachaPhase.Started
                )
            }
        }
    }
    fun skipRevealAnimation() = viewModelScope.launch {
        intent {
            clearMediaItems()
            reduce {
                state.copy(
                    phase = GachaPhase.Reveal
                )
            }
        }
    }

    private suspend fun clearMediaItems() = withContext(Dispatchers.Main) {
        exoPlayer.clearMediaItems()
    }
    fun revealNext(nextIdx: Int, pull: CookieDrawResult) = viewModelScope.launch {
        intent {
            if (nextIdx > pull.result.lastIndex) {
                reduce {
                    state.copy(phase = GachaPhase.End, revealIdx = 0)
                }
                return@intent
            }
            playGachaRevealAnimation.invoke(
                exoPlayer = state.player,
                cookieDraw = pull.result[nextIdx],
                onStart = {
                    reduce {
                        state.copy(
                            phase = GachaPhase.RevealAnimation,
                            revealIdx = nextIdx
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



