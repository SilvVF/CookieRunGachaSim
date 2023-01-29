package io.silv.crcsim.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CookieEntity(

    @PrimaryKey(autoGenerate = false)
    val name: String,

    @ColumnInfo(name = "soulstone_count")
    val soulstoneCount: Int,
)