package io.silv.crcsim.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArtifactEntity(

    @PrimaryKey(autoGenerate = false)
    val name: String,

    @ColumnInfo("soulstone_count")
    val soulstoneCount: Int
)
