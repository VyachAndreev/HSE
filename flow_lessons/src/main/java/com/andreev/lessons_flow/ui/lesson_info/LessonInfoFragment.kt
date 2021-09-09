package com.andreev.lessons_flow.ui.lesson_info

import com.andreev.core.base.BaseFragment
import com.andreev.lessons_flow.R
import com.andreev.lessons_flow.databinding.FragmentLessonsBinding
import com.andreev.lessons_flow.ui.lessons.LessonsViewModel

class LessonInfoFragment: BaseFragment<FragmentLessonsBinding, LessonsViewModel>() {
    override fun getLayoutRes(): Int = R.layout.fragment_lesson_info
}