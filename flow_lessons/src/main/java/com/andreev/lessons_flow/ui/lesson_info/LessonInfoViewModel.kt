package com.andreev.lessons_flow.ui.lesson_info

import androidx.lifecycle.MutableLiveData
import com.andreev.core.base.BaseViewModel
import com.andreev.data.models.Lesson
import com.andreev.lessons_flow.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class LessonInfoViewModel : BaseViewModel() {
    val type = MutableLiveData<String>()
    val discipline = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val online = MutableLiveData<String>()
    val teacher = MutableLiveData<String>()
    val teacherPosition = MutableLiveData<String>()
    val link = MutableLiveData<String>()
    val extraLink = MutableLiveData<String>()
    val info = MutableLiveData<String>()
    val lesson = MutableLiveData<Lesson>()

    fun getLesson(id: String) {
        scopeMain.launch {
            try {
                lesson.value = withContext(Dispatchers.IO) {
                    api.getLesson(id)
                }
                Timber.i("""GET: /lesson
                        |${lesson.value}
                    """.trimMargin())
            } catch (e: retrofit2.HttpException) {
                Timber.e("Exception: ${e.printStackTrace()}")
                errorMessage.value = R.string.something_went_wrong
            }
        }
    }
}