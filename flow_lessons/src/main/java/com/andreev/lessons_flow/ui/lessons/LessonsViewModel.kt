package com.andreev.lessons_flow.ui.lessons

import androidx.lifecycle.MutableLiveData
import com.andreev.core.base.BaseViewModel
import com.andreev.data.models.Lesson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class LessonsViewModel: BaseViewModel() {

    val lessons = MutableLiveData<Array<Lesson>>()

    fun getLessons() {
        try {
            scopeMain.launch {
                lessons.value = withContext(Dispatchers.IO) {
                    api.getLessons()
                }
                Timber.i("""GET: /lessons
                    |${lessons.value}
                """.trimMargin())
            }
        } catch (e: retrofit2.HttpException) {
            Timber.e(e)
        }
    }
}