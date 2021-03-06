package com.andreev.data.db

import androidx.room.*
import com.andreev.data.models.Lesson
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

    @Query(deleteUnused)
    fun deleteUnusedLessons(date_start: Date)

    private companion object {
        const val whereDateCall = "SELECT * FROM Lesson WHERE date_start == :date_start"
        const val selectAllCall = "SELECT * FROM Lesson"
        const val deleteUnused = "DELETE FROM Lesson WHERE date_start < :date_start"
    }
}