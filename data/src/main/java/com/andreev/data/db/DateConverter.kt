package com.andreev.data.db

import androidx.room.TypeConverter
import com.andreev.data.utils.DateUtils
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromString(string: String?): Date? {
        return DateUtils.parseDate(string)
    }

    @TypeConverter
    fun fromDate(date: Date?): String? {
        return DateUtils.formatSimpleDateDay(date)
    }
}