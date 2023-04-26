package io.silv.crcsim.feat_artifact_gacha.compose

import io.silv.crcsim.feat_artifact_gacha.Artifact

sealed class ArtifactRoute(val route: String) {

    object Waiting: ArtifactRoute("waiting")
    object Start: ArtifactRoute("start")
    data class Reveal(val idx: Int): ArtifactRoute( "reveal/{idx}")
    data class RevealIdle(val idx: Int): ArtifactRoute( "reveal-idle/{idx}")
    object End:  ArtifactRoute("end")
}