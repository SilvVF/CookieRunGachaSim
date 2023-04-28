package io.silv.crcsim.feat_ui_inventory.usecase

import io.silv.crcsim.data.Treasure
import io.silv.crcsim.data.room.dao.CookieDao
import io.silv.crcsim.data.room.dao.TreasureDao
import io.silv.crcsim.data.room.entity.CookieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun interface InventoryCookieFlow: () -> Flow<List<InventoryCookie>>
fun interface InventoryTreasureFlow: () -> Flow<List<InventoryTreasure>>

fun inventoryCookieFlowImpl(
    cookieDao: CookieDao,
): Flow<List<InventoryCookie>> {
    return cookieDao.allCookiesFlow().map { list: List<CookieEntity> ->
        list.map { cookieEntity ->
            InventoryCookie(
                cookie = cookieEntity.name,
                count = cookieEntity.soulstoneCount
            )
        }
    }
}

fun inventoryTreasureFlowImpl(
    treasureDao: TreasureDao
): Flow<List<InventoryTreasure>> {
    return treasureDao.allTreasuresFlow().map { list ->
        list.map { treasure ->
            InventoryTreasure(
                artifact = Treasure.valueOf(treasure.name),
                count = treasure.count
            )
        }
    }
}

data class InventoryTreasure(
    val artifact: Treasure,
    val count: Int
)

data class InventoryCookie (
    val cookie: String,
    val count: Int
)