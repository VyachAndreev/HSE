package com.andreev.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andreev.data.db.converters.DateConverter
import com.andreev.data.db.converters.IntArrayConverter
import com.andreev.data.db.converters.PropertyConverter
import com.andreev.data.models.Lesson

@Database(entities = [Lesson::class], version = 1)
@TypeConverters(DateConverter::class, IntArrayConverter::class, PropertyConverter::class)
abstract class LessonDatabase : RoomDatabase() {
    abstract fun dao(): DAO

    companion object {
        private const val name = "DB"
        private var instance: LessonDatabase? = null

        fun getLessonDatabase(context: Context): LessonDatabase? {
            if (instance == null) {
                synchronized(LessonDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LessonDatabase::class.java,
                        name,
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }

        fun destroyDB() {
            instance = null
        }
    }
}