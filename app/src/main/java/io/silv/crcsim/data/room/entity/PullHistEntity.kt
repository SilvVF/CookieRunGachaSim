package io.silv.crcsim.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "pull_hist")
data class PullHistEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    /**
     * type string either "treasure" or "cookie"
     */
    @ColumnInfo(name = "type")
    val type: String,

    /**
     * items - string name for item amount is at same index in amounts list
     */
    @ColumnInfo(name = "items")
    val items: List<String>,

    /**
     * amounts - amount of the item obtained at same index in items list
     */
    @ColumnInfo(name = "amounts")
    val amounts: List<Int>,
) {
    companion object {
        const val treasureType = "treasure"
        const val cookieType = "cookie"
    }
}

class PullHistConverters {

    @TypeConverter
    fun fromStringToListOfString(s: String): List<String> {
        return s.split(",")
    }

    @TypeConverter
    fun fromListOfStringToString(l: List<String>): String {
        return l.joinToString(",")
    }

    @TypeConverter
    fun fromStringToListOfInt(s: String): List<Int> {
        return s.split(",").map { it.toInt() }
    }

    @TypeConverter
    fun fromListOfIntToString(l: List<Int>): String {
        return l.joinToString(",")
    }
}

