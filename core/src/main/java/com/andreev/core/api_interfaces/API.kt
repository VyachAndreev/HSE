package com.andreev.core.api_interfaces

import com.andreev.data.models.Lesson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET(lessonsUrl)
    suspend fun getLessons(
        @Query("email") email: String = "vsandreev@edu.hse.ru",
        @Query("start") startDate: String? = "2021-09-10",
    ) : Array<Lesson>

    private companion object {
        const val lessonsUrl = "lessons"
    }
}