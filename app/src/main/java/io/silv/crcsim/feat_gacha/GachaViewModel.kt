package io.silv.crcsim.feat_gacha

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.silv.crcsim.feat_gacha.container.GachaEffect
import io.silv.crcsim.feat_gacha.container.GachaState
import io.silv.crcsim.feat_gacha.usecases.DrawCookiesUseCase
import io.silv.crcsim.feat_gacha.usecases.UserDataRepo
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.syntax.simple.repeatOnSubscription
import org.orbitmvi.orbit.viewmodel.container
class GachaViewModel(
    private val drawCookies: DrawCookiesUseCase,
    private val userDataRepo: UserDataRepo
): ViewModel(), ContainerHost<GachaState, GachaEffect> {

    override val container = container<GachaState, GachaEffect>(
        GachaState()
    ) { _ ->
        init()
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
            val pull = drawCookies(pity = state.pity, 10)
            reduce {
                state.copy(
                    pull = pull
                )
            }
        }
    }

    fun handleDraw1Click() = viewModelScope.launch {
        intent {
            val pull = drawCookies(pity = state.pity, 1)
            reduce {
                state.copy(
                    pull = pull
                )
            }
        }
    }
}
