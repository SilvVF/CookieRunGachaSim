package io.silv.crcsim.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cookie")
data class CookieEntity(

    @PrimaryKey(autoGenerate = false)
    val name: String,

    @ColumnInfo(name = "soulstone_count")
    val soulstoneCount: Int,
)