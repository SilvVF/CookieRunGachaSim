package io.silv.crcsim.feat_artifact_gacha

import io.silv.crcsim.data.datastore.UserDataStore
import io.silv.crcsim.data.room.dao.ArtifactDao
import io.silv.crcsim.data.room.dao.PullHistDao
import io.silv.crcsim.data.room.entity.PullHistEntity
import io.silv.crcsim.models.Rarity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.time.LocalDateTime
import kotlin.random.Random
import kotlin.reflect.typeOf

fun interface DrawArtifactUseCase: suspend (Int) -> ArtifactDrawResult

private val commonRange = 0.0..58.0
private val rareRange = 58.1..88.0
private val epicRange = 88.1..100.0

private const val artifactDrawCost = 1

suspend fun drawArtifactUseCase(
    amount: Int,
    artifactDao: ArtifactDao,
    histDao: PullHistDao,
    dataStore: UserDataStore
): ArtifactDrawResult {
    return artifactGachaSim(amount).also { artifactDraw ->
        coroutineScope {
            launch {
                artifactDraw.result.forEach { (name, count, _) ->
                    with(artifactDao) {
                        val entity = getArtifactByName(name)
                        upsertArtifact(
                            entity.copy(soulstoneCount = entity.soulstoneCount + count)
                        )
                    }
                }
            }
            launch {
                dataStore.addCrystals(amount * artifactDrawCost)
            }
            launch {
                histDao.addHistoryItem(
                    PullHistEntity(
                        type = "artifact",
                        items = artifactDraw.result.map { it.name },
                        amounts = artifactDraw.result.map { it.amount }
                    )
                )
            }
        }
    }
}

private fun artifactGachaSim(amount: Int): ArtifactDrawResult {

    fun drawArtifact(): Artifact = when(Random.nextDouble(until = 100.0)) {
            in commonRange -> Artifact("", 1, Rarity.Common)
            in rareRange -> Artifact("", 1, Rarity.Epic)
            else -> Artifact("", 1, Rarity.Rare)
    }

    return ArtifactDrawResult(
        result = buildList {
            repeat(amount) {
                add(drawArtifact())
            }
        },
        time = LocalDateTime.now()
    )
}

data class Artifact(
    val name: String,
    val amount: Int,
    val rarity: Rarity
)