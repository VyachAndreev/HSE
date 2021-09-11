package com.andreev.lessons_flow.ui.lesson_info

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andreev.core.base.BaseFragment
import com.andreev.core.di.ApplicationComponent
import com.andreev.data.models.Lesson
import com.andreev.lessons_flow.R
import com.andreev.lessons_flow.databinding.FragmentLessonInfoBinding
import com.andreev.lessons_flow.ui.Constants

class LessonInfoFragment: BaseFragment<FragmentLessonInfoBinding>() {
    private lateinit var viewModel: LessonInfoViewModel
    private var lessonId: String?  = null

    override fun getLayoutRes(): Int = R.layout.fragment_lesson_info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lessonId = savedInstanceState?.getString(Constants.lessonId)
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        viewModel = ViewModelProvider(this).get(LessonInfoViewModel::class.java)
        viewModel.injectDependencies(applicationComponent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        with(binding) {
            swipeLayout.isRefreshing = true
            swipeLayout.setOnRefreshListener { lessonId?.let {
                this@LessonInfoFragment.viewModel.getLesson(it)
            } }
            backImage.setOnClickListener { onBackPressed() }
        }
        lessonId = arguments?.getString(Constants.lessonId, null)
        lessonId?.let { viewModel.getLesson(it) }
        viewModel.errorMessage.observe(viewLifecycleOwner, errorMessageObserver)
        viewModel.lesson.observe(viewLifecycleOwner, lessonObserver)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(Constants.lessonId, lessonId)
    }

    private val lessonObserver = Observer<Lesson> {
        with(binding) {
            if (parentRelative.visibility == gone) {
                parentRelative.visibility = visible
            }
            swipeLayout.isRefreshing = false
            if (this@LessonInfoFragment.viewModel.lesson.value?.lecturer == null) {
                professorNameTv.visibility = gone
                professorTv.visibility = gone
                professorView.visibility = gone
            } else {
                professorNameTv.visibility = visible
                professorTv.visibility = visible
                professorView.visibility = visible
            }
        }
    }
}