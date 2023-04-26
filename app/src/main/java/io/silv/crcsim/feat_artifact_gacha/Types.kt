package io.silv.crcsim.feat_artifact_gacha

import java.time.LocalDateTime

data class ArtifactDrawResult(
    val result: List<Artifact>,
    val time: LocalDateTime
)