package com.example.pddiary.network.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "user_events")
data class UserEvent(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val eventType: String,
    val eventTimestamps: List<String>? = null,
    val visitCount: Int? = null,
    val date: String? = null,
    val screenName: String? = null,
    val buttonClickedName: String? = null,
    val durationSeconds: Int? = null
)

class TypeConverters {
    @TypeConverter
    fun fromTimestampList(value: List<String>?): String {
        return value?.joinToString(",") ?: ""
    }

    @TypeConverter
    fun toTimestampList(value: String): List<String> {
        return value.split(",")
    }
}