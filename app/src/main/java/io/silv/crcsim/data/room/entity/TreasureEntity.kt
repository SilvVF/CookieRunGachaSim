package io.silv.crcsim.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("treasure")
data class TreasureEntity(

    @PrimaryKey(autoGenerate = false)
    val name: String,

    @ColumnInfo("count")
    val count: Int
)
