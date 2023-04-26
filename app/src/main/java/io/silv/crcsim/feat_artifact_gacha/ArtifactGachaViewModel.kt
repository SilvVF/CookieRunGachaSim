package io.silv.crcsim.feat_artifact_gacha

import androidx.lifecycle.ViewModel
import io.silv.crcsim.feat_artifact_gacha.container.ArtifactGachaEffect
import io.silv.crcsim.feat_artifact_gacha.container.ArtifactGachaState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ArtifactGachaViewModel(
    private val drawArtifact: DrawArtifactUseCase
): ViewModel(), ContainerHost<ArtifactGachaState, ArtifactGachaEffect> {

    override val container = container<ArtifactGachaState, ArtifactGachaEffect>(ArtifactGachaState())

    fun drawArtifacts(amount: Int) = intent {
        val result = drawArtifact(amount)
        reduce {
            state.copy(artifactDrawResult = result)
        }
    }
}

