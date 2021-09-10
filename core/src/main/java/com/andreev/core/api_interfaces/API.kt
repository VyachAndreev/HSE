package com.andreev.core.api_interfaces

import com.andreev.data.models.Lesson
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @Throws(retrofit2.HttpException::class)
    @GET(lessonsUrl)
    suspend fun getLessons(
        @Query(email) eMail: String = testEmail,
        @Query(start) startDate: String? = testStart,
        @Query(end) endDate: String? = testEnd,
    ) : Array<Lesson>

    @Throws(retrofit2.HttpException::class)
    @GET(lessonUrl)
    suspend fun getLesson(@Path(id) disciplineId: String?): Lesson

    private companion object {
        const val email = "email"
        const val testEmail = "vsandreev@edu.hse.ru"
        const val start = "start"
        const val testStart = "2021-09-10"
        const val end = "end"
        const val testEnd = "2021-09-10"
        const val id = "id"
        const val lessonsUrl = "lessons"
        const val lessonUrl = "lesson"
    }
}