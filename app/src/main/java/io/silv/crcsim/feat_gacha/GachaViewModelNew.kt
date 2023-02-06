package io.silv.crcsim.feat_gacha

import androidx.lifecycle.ViewModel
import io.silv.crcsim.feat_gacha.container.GachaEffect
import io.silv.crcsim.feat_gacha.usecases.CookieDrawResult
import io.silv.crcsim.feat_gacha.usecases.DrawCookiesUseCase
import io.silv.crcsim.feat_gacha.usecases.Pity
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDateTime

class GachaViewModelNew(
    private val drawCookies: DrawCookiesUseCase,
): ViewModel(), ContainerHost<GachaStateNew, GachaEffect> {

    override val container = container<GachaStateNew, GachaEffect> (
        GachaStateNew()
    )

    fun performGachaPull(amount: Int) = intent {
        val pull = drawCookies(pity = Pity(), amount)
        reduce {
            state.copy(
                pull = pull
            )
        }
    }

}

data class GachaStateNew(
    val pull: CookieDrawResult = CookieDrawResult(Pity(), emptyList(), LocalDateTime.now()),
)