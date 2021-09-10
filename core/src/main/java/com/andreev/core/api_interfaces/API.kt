package com.andreev.core.api_interfaces

import com.andreev.data.models.Lesson
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @Throws(retrofit2.HttpException::class)
    @GET(lessonsUrl)
    suspend fun getLessons(
        @Query(email_url) email: String = testEmail,
        @Query(start_url) startDate: String? = testStart,
        @Query(end_url) endDate: String? = testEnd,
    ) : Array<Lesson>

    @Throws(retrofit2.HttpException::class)
    @GET(lessonUrl)
    suspend fun getLesson(@Path(id_url) id: String?): Lesson

    private companion object {
        const val email_url = "email"
        const val testEmail = "vsandreev@edu.hse.ru"
        const val start_url = "start"
        const val testStart = "2021-09-10"
        const val end_url = "end"
        const val testEnd = "2021-09-10"
        const val id_url = "id"
        const val lessonsUrl = "lessons"
        const val lessonUrl = "lessons/{id}"
    }
}