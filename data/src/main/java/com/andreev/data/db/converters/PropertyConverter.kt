package com.andreev.data.db.converters

import androidx.room.TypeConverter
import com.andreev.data.models.Property

class PropertyConverter {
    @TypeConverter
    fun fromString(string: String?): Array<Property?>? {
        return string?.split(';')?.map {
            val stringList = string.split(',')
            Property(stringList[0], stringList[1])
        }?.toTypedArray()
    }

    @TypeConverter
    fun fromProperty(properties: Array<Property>?): String? {
        return properties?.let {
            it.joinToString { property ->
                "${property.description},${property.link};"
            }
        }
    }
}