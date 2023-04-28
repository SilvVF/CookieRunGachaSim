package io.silv.crcsim.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.silv.crcsim.data.room.entity.PullHistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PullHistDao {

    @Query("SELECT * FROM pull_hist")
    fun getHistory(): Flow<List<PullHistEntity>>

    @Query("SELECT * FROM pull_hist WHERE type = 'cookie' ")
    fun getCookieHistory(): Flow<List<PullHistEntity>>

    @Query("SELECT * FROM pull_hist WHERE type = '${PullHistEntity.treasureType}'")
    fun getTreasureHistory(): Flow<List<PullHistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistoryItem(pullHistEntity: PullHistEntity)

    @Query("DELETE FROM pull_hist")
    suspend fun clearHistory()
}