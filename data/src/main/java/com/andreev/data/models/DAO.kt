package com.andreev.data.models

import androidx.room.*
import java.util.*

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLesson(lesson: Lesson)

    @Update
    fun updateLesson(lesson: Lesson)

    @Delete
    fun deleteLesson(lesson: Lesson)

    @Query(whereDateCall)
    fun selectLessonWhereDate(date_start: Date): Array<Lesson>

    @Query(selectAllCall)
    fun getLessons(): Array<Lesson>

    private companion object {
        const val whereDateCall = "SELECT * FROM Lesson WHERE date_start == :date_start"
        const val selectAllCall = "SELECT * FROM Lesson"
    }
}