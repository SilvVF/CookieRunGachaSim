package io.silv.crcsim.feat_treasure_gacha.domain

import io.silv.crcsim.data.Treasure
import io.silv.crcsim.data.datastore.UserDataStore
import io.silv.crcsim.data.room.dao.PullHistDao
import io.silv.crcsim.data.room.dao.TreasureDao
import io.silv.crcsim.data.room.entity.PullHistEntity
import io.silv.crcsim.data.room.entity.TreasureEntity
import io.silv.crcsim.feat_treasure_gacha.TreasureDrawResult
import io.silv.crcsim.models.Rarity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.random.Random

fun interface DrawTreasureUseCase: suspend (Int) -> TreasureDrawResult

private val commonRange = 0.0..58.0
private val rareRange = 58.1..88.0
private val epicRange = 88.1..100.0

private const val treasureDrawCost = 200

suspend fun drawTreasureUseCase(
    amount: Int,
    treasureDao: TreasureDao,
    histDao: PullHistDao,
    dataStore: UserDataStore
): TreasureDrawResult {
    return treasureGachaSim(amount).also { artifactDraw ->
        coroutineScope {
            launch {
                artifactDraw.result.forEach { treasure ->
                    with(treasureDao) {
                        upsertTreasure(
                            TreasureEntity(
                                name = treasure.id,
                                count = (getTreasureByName(treasure.id)?.count ?: 0) + 1
                            )
                        )
                    }
                }
            }
            launch {
                dataStore.addCrystals(amount * treasureDrawCost)
            }
            launch {
                histDao.addHistoryItem(
                    PullHistEntity(
                        type = PullHistEntity.treasureType,
                        items = artifactDraw.result.map { it.name },
                        amounts = artifactDraw.result.map { 1 }
                    )
                )
            }
        }
    }
}


private fun treasureGachaSim(amount: Int): TreasureDrawResult {



    fun drawArtifact(): Treasure = when(Random.nextDouble(until = 100.0)) {
        in commonRange -> Treasure.values().filter { it.rarity == Rarity.Common }.random()
        in rareRange -> Treasure.values().filter { it.rarity == Rarity.Rare }.random()
        else ->  Treasure.values().filter { it.rarity in listOf(Rarity.Special, Rarity.Epic) }.random()
    }

    return TreasureDrawResult(
        result = buildList {
            repeat(amount) {
                add(drawArtifact())
            }
        },
        time = LocalDateTime.now()
    )
}
