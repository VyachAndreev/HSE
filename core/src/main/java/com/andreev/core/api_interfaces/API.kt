package com.andreev.core.api_interfaces

import com.andreev.data.models.Lesson
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @Throws(retrofit2.HttpException::class)
    @GET(lessonsUrl)
    suspend fun getLessons(
        @Query("email") email: String = "vsreev@edu.hse.ru",
        @Query("start") startDate: String? = "2021-09-10",
        @Query("end") endDate: String? = "2021-09-10"
    ) : Array<Lesson>

    private companion object {
        const val lessonsUrl = "lessons"
    }
}