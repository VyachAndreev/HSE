package com.andreev.lessons_flow.ui.lesson_info

import androidx.lifecycle.MutableLiveData
import com.andreev.core.base.BaseViewModel
import com.andreev.data.models.Lesson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            lesson.value = withContext(Dispatchers.IO) {
                api.getLesson(id)
            }
        }
    }
}