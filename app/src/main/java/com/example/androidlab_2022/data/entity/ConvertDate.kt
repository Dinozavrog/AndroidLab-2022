package com.example.androidlab_2022.data.entity

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.*

@ProvidedTypeConverter
class ConvertDate {
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun timestampToDate(timestamp: Long?): Date? {
        return timestamp?.let {
            Date(it)
        }
    }
}