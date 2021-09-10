package com.andreev.lessons_flow.ui.lessons

import androidx.lifecycle.MutableLiveData
import retrofit2.Callback
import com.andreev.core.api_interfaces.LessonsAPI
import com.andreev.core.base.BaseViewModel
import com.andreev.core.di.ApplicationComponent
import com.andreev.data.models.Lesson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class LessonsViewModel: BaseViewModel() {
    @Inject
    lateinit var lessonsAPI: LessonsAPI

    val lessons = MutableLiveData<Array<Lesson>>()

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    fun getLessons() {
        try {
            scopeIO.launch {
                lessonsAPI.getLessons().enqueue(object : Callback<Array<Lesson>> {
                    override fun onResponse(
                        call: Call<Array<Lesson>>,
                        response: Response<Array<Lesson>>
                    ) {
                        response.body()?.let {
                            Timber.i(
                                """GET: /lessons
                            |$it
                        """.trimMargin()
                            )
                            lessons.postValue(it)
                        }
                    }

                    override fun onFailure(call: Call<Array<Lesson>>, t: Throwable) {
                        throw t
                    }
                })
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}