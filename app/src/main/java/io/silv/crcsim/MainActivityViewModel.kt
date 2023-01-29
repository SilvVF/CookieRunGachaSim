package io.silv.crcsim

import androidx.lifecycle.ViewModel
import io.silv.crcsim.data.allCookies
import io.silv.crcsim.data.getAllCookieNames
import io.silv.crcsim.data.room.CookieDao
import io.silv.crcsim.data.room.CookieEntity
import kotlinx.coroutines.flow.first
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container

class MainActivityViewModel: ViewModel(), ContainerHost<MainState, MainEffect> {

    override val container = container<MainState, MainEffect>(
        initialState = MainState()
    ) {
        init()
    }

    private fun init() = intent {

    }
}

data class MainState(
    val loading: Boolean = false
)


sealed interface MainEffect