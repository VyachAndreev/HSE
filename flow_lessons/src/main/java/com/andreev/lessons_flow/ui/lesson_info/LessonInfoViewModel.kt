package com.andreev.lessons_flow.ui.lesson_info

import androidx.lifecycle.MutableLiveData
import com.andreev.core.base.BaseViewModel
import com.andreev.data.utils.DateUtils
import com.andreev.data.models.Lesson
import com.andreev.lessons_flow.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class LessonInfoViewModel : BaseViewModel() {
    val type = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val lesson = MutableLiveData<Lesson>()

    fun getLesson(id: String) {
        scopeMain.launch {
            try {
                lesson.value = withContext(Dispatchers.IO) {
                    api.getLesson(id)
                }
                if (lesson.value != null) {
                    time.value = "${DateUtils.formatSimpleDate(lesson.value!!.date_start)}-${
                        DateUtils.formatSimpleDate(
                        lesson.value!!.date_end)}"
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