package io.silv.crcsim

import androidx.lifecycle.ViewModel
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