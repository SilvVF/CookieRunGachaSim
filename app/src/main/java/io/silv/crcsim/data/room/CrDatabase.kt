package io.silv.crcsim.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [CookieEntity::class, ArtifactEntity::class],
    exportSchema = true
)
abstract class CrDatabase: RoomDatabase() {

    abstract fun cookieDao(): CookieDao

    abstract fun artifactDao(): ArtifactDao
}