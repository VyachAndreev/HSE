package com.andreev.data.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Lesson::class], version = 1)
abstract class LessonDatabase: RoomDatabase() {
    abstract fun dao(): DAO

    companion object {
        var instance: LessonDatabase? = null

        fun getLessonDatabase(context: Context): LessonDatabase? {
            if (instance == null) {
                synchronized(LessonDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LessonDatabase::class.java,
                        "DB"
                    ).build()
                }
            }
            return instance
        }

        fun destroyDB() {
            instance = null
        }
    }
}