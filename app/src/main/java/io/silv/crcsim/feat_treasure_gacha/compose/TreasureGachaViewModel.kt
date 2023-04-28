package io.silv.crcsim.feat_treasure_gacha.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.silv.crcsim.feat_cookie_gacha.HistoryFilter
import io.silv.crcsim.feat_cookie_gacha.domain.FetchTreasureHistory
import io.silv.crcsim.feat_cookie_gacha.domain.FetchCookieHistoryUseCase
import io.silv.crcsim.feat_cookie_gacha.domain.FetchHistoryUseCase
import io.silv.crcsim.feat_cookie_gacha.domain.UserDataRepo
import io.silv.crcsim.feat_treasure_gacha.ArtifactGachaEffect
import io.silv.crcsim.feat_treasure_gacha.ArtifactGachaState
import io.silv.crcsim.feat_treasure_gacha.TreasureDrawResult
import io.silv.crcsim.feat_treasure_gacha.domain.DrawTreasureUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.syntax.simple.repeatOnSubscription
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDateTime

class TreasureGachaViewModel(
    private val drawTreasure: DrawTreasureUseCase,
    private val userDataRepo: UserDataRepo,
    fetchCookieHistory: FetchCookieHistoryUseCase,
    fetchHistory: FetchHistoryUseCase,
    fetchTreasureHistory: FetchTreasureHistory
): ViewModel(), ContainerHost<ArtifactGachaState, ArtifactGachaEffect> {

    override val container = container<ArtifactGachaState, ArtifactGachaEffect>(ArtifactGachaState()) {
        init()
    }
    private fun init() = intent {
        repeatOnSubscription {
            userDataRepo.crystalsFlow.collect {
                reduce { state.copy(crystals = it) }
            }
        }
    }

    private var _currentFilter = MutableStateFlow<HistoryFilter>(HistoryFilter.None)
    val currentFilter = _currentFilter.asStateFlow()
    fun changeFilter(filter: HistoryFilter) = viewModelScope.launch {
        _currentFilter.emit(filter)
    }

    val history = combine(
        fetchCookieHistory(),
        fetchHistory(),
        fetchTreasureHistory(),
        _currentFilter
    ) { cookieHistory, allHistory, artifactHistory, filter ->
        when(filter) {
            HistoryFilter.Cookie -> cookieHistory
            is HistoryFilter.Treasure -> artifactHistory
            else -> allHistory
        }
    }
    fun drawTreasureClicked(amount: Int) = intent {
        val result = drawTreasure(amount)
        reduce {
            state.copy(treasureDrawResult = result)
        }
    }

    fun showNextTreasure() = intent {
        if (state.currentTreasureIdx < state.treasureDrawResult.result.lastIndex ) {
            reduce {
                state.copy(currentTreasureIdx = state.currentTreasureIdx + 1)
            }
        } else {
            reduce {
                state.copy(
                    currentTreasureIdx = 0,
                    treasureDrawResult = TreasureDrawResult(
                        emptyList(),
                        LocalDateTime.now()
                    )
                )
            }
        }
    }
}

