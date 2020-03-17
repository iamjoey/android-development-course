package com.example.rockpaperscissors

import androidx.room.TypeConverter
import java.util.*

// https://gist.github.com/tinmegali/d4a477785f01e57066915a44543db6ed
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}