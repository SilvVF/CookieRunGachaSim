package io.silv.crcsim.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CookieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCookie(vararg cookieEntity: CookieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCookie(vararg cookieEntity: CookieEntity)

    @Update
    suspend fun updateCookie(vararg cookieEntity: CookieEntity)

    @Query("SELECT * FROM cookieentity")
    fun allCookiesFlow(): Flow<List<CookieEntity>?>

    @Query(
        "SELECT * FROM cookieentity WHERE name = :name LIMIT 1"
    )
    fun getCookieByName(name: String): CookieEntity?
}