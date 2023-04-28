package io.silv.crcsim.feat_ui_inventory.usecase

import io.silv.crcsim.data.Cookie
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
        list.mapNotNull { cookieEntity ->
            runCatching {
                InventoryCookie(
                    cookie = Cookie.valueOf(cookieEntity.name.replace("_", "")),
                    count = cookieEntity.soulstoneCount
                )
            }.getOrNull()
        }
    }
}

fun inventoryTreasureFlowImpl(
    treasureDao: TreasureDao
): Flow<List<InventoryTreasure>> {
    return treasureDao.allTreasuresFlow().map { list ->
        list.mapNotNull { treasure ->
            runCatching {
                InventoryTreasure(
                    treasure = Treasure.valueOf(treasure.name.replace("_", "")),
                    count = treasure.count
                )
            }.getOrNull()
        }
    }
}

data class InventoryTreasure(
    val treasure: Treasure,
    val count: Int
)

data class InventoryCookie (
    val cookie: Cookie,
    val count: Int
)