package io.silv.crcsim.feat_artifact_gacha.container

import io.silv.crcsim.feat_artifact_gacha.Artifact
import io.silv.crcsim.feat_artifact_gacha.ArtifactDrawResult
import java.time.LocalDateTime

data class ArtifactGachaState(
    val artifactDrawResult: ArtifactDrawResult = ArtifactDrawResult(emptyList(), LocalDateTime.now())
)
