package io.silv.crcsim.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtifactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertArtifact(vararg artifactEntity: ArtifactEntity)

    @Update
    suspend fun updateArtifact(vararg artifactEntity: ArtifactEntity)

    @Query("""
        SELECT * FROM artifactentity
    """)
    fun allArtifactsFlow(): Flow<List<ArtifactEntity>>
}