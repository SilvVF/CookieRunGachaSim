package io.silv.crcsim.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.silv.crcsim.data.room.dao.ArtifactDao
import io.silv.crcsim.data.room.dao.CookieDao
import io.silv.crcsim.data.room.dao.PullHistDao
import io.silv.crcsim.data.room.entity.ArtifactEntity
import io.silv.crcsim.data.room.entity.CookieEntity
import io.silv.crcsim.data.room.entity.PullHistConverters
import io.silv.crcsim.data.room.entity.PullHistEntity

@Database(
    version = 2,
    entities = [CookieEntity::class, ArtifactEntity::class, PullHistEntity::class],
    exportSchema = true
)
@TypeConverters(PullHistConverters::class)
abstract class CrDatabase: RoomDatabase() {

    abstract fun cookieDao(): CookieDao

    abstract fun artifactDao(): ArtifactDao

    abstract fun pullHistDao(): PullHistDao
}