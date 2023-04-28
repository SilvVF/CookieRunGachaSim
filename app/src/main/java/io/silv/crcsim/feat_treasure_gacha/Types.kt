package io.silv.crcsim.feat_treasure_gacha

import io.silv.crcsim.data.Treasure
import java.time.LocalDateTime

data class TreasureDrawResult(
    val result: List<Treasure>,
    val time: LocalDateTime,
)

data class ArtifactGachaState(
    val treasureDrawResult: TreasureDrawResult = TreasureDrawResult(emptyList(), LocalDateTime.now()),
    val crystals: Int = 0
)

sealed interface ArtifactGachaEffect


