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
        if (array == null) {
            return null
        }
        var string = ""
        array.forEachIndexed { index, i ->
            string += if (index != array.size - 1) {
                "$i,"
            } else {
                "$i"
            }
        }
        return string
    }
}