package io.silv.crcsim.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.silv.crcsim.data.room.entity.TreasureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TreasureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTreasure(vararg treasureEntity: TreasureEntity)

    @Update
    suspend fun updateTreasure(vararg treasureEntity: TreasureEntity)

    @Query("SELECT * FROM treasure WHERE name = :name LIMIT 1")
    suspend fun getTreasureByName(name: String): TreasureEntity?

    @Query("""
        SELECT * FROM treasure
    """)
    fun allTreasuresFlow(): Flow<List<TreasureEntity>>
}