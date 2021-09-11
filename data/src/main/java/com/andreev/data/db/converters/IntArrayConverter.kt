package com.andreev.data.db.converters

import androidx.room.TypeConverter

class IntArrayConverter {
    @TypeConverter
    fun fromString(string: String?): Array<Int>? {
        return string?.split(',')?.map {
            it.toInt()
        }?.toTypedArray()
    }

    @TypeConverter
    fun fromArray(array: Array<Int>?): String? {
        return array?.joinToString {
            "$it,"
        }
    }
}