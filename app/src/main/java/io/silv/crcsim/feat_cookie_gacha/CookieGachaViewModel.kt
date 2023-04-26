package io.silv.crcsim.feat_cookie_gacha

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewModelScope
import io.silv.crcsim.feat_cookie_gacha.container.GachaEffect
import io.silv.crcsim.feat_cookie_gacha.container.GachaState
import io.silv.crcsim.feat_cookie_gacha.container.HistoryFilter
import io.silv.crcsim.feat_cookie_gacha.gacha.DrawCookiesUseCase
import io.silv.crcsim.feat_cookie_gacha.gacha.FetchArtifactHistoryUseCase
import io.silv.crcsim.feat_cookie_gacha.gacha.FetchCookieHistoryUseCase
import io.silv.crcsim.feat_cookie_gacha.gacha.FetchHistoryUseCase
import io.silv.crcsim.feat_cookie_gacha.gacha.UserDataRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.syntax.simple.repeatOnSubscription
import org.orbitmvi.orbit.viewmodel.container

class CookieGachaViewModel(
    private val drawCookies: DrawCookiesUseCase,
    fetchCookieHistory: FetchCookieHistoryUseCase,
    fetchArtifactHistory: FetchArtifactHistoryUseCase,
    fetchHistory: FetchHistoryUseCase,
    private val userDataRepo: UserDataRepo
): ViewModel(), ContainerHost<GachaState, GachaEffect> {

    override val container = container<GachaState, GachaEffect>(
        GachaState()
    ) { _ ->
        init()
    }

    private var _currentFilter = MutableStateFlow<HistoryFilter>(HistoryFilter.None)
    val currentFilter = _currentFilter.asStateFlow()
    fun changeFilter(filter: HistoryFilter) = viewModelScope.launch {
        _currentFilter.emit(filter)
    }

    val history = combine(
        fetchCookieHistory(),
        fetchHistory(),
        fetchArtifactHistory(),
        _currentFilter
    ) { cookieHistory, allHistory, artifactHistory, filter ->
        when(filter) {
            HistoryFilter.Cookie -> cookieHistory
            is HistoryFilter.Artifact -> artifactHistory
            else -> allHistory
        }
    }


    private fun init() = intent {
        viewModelScope.launch {
            repeatOnSubscription {
                userDataRepo.pityFlow.collect { pity ->
                    println("Pity: $pity")
                    reduce {
                        state.copy(pity = pity)
                    }
                }
            }
        }
        viewModelScope.launch {
            repeatOnSubscription {
                userDataRepo.crystalsFlow.collect  { total ->
                    println("Crystals: $total")
                    reduce {
                        state.copy(crystalsSpent = total)
                    }
                }
            }
        }
    }


    fun handleDraw10Click() = viewModelScope.launch {
        intent {
            val pull = drawCookies(state.pity, 10)
            reduce {
                state.copy(
                    pull = pull
                )
            }
        }
    }

    fun handleDraw1Click() = viewModelScope.launch {
        intent {
            val pull = drawCookies(state.pity, 1)
            reduce {
                state.copy(
                    pull = pull
                )
            }
        }
    }
}
