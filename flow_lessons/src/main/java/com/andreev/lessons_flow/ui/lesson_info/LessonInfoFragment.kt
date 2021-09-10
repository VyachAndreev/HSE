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
import timber.log.Timber

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
        binding.swipeLayout.isRefreshing = true
        binding.viewModel = viewModel
        lessonId = arguments?.getString(Constants.lessonId, null)
        lessonId?.let { viewModel.getLesson(it) }
        viewModel.errorMessage.observe(viewLifecycleOwner, errorMessageObserver)
        viewModel.lesson.observe(viewLifecycleOwner, lessonObserver)
        binding.swipeLayout.setOnRefreshListener {
            lessonId?.let { viewModel.getLesson(it) }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(Constants.lessonId, lessonId)
    }

    private val lessonObserver = Observer<Lesson> {
        if (binding.parentRelative.visibility == View.GONE) {
            binding.parentRelative.visibility = View.VISIBLE
        }
        binding.swipeLayout.isRefreshing = false
        if (viewModel.lesson.value?.lecturer == null) {
            binding.professorNameTv.visibility = View.GONE
            binding.professorTv.visibility = View.GONE
            binding.professorView.visibility = View.GONE
        } else {
            binding.professorNameTv.visibility = View.VISIBLE
            binding.professorTv.visibility = View.VISIBLE
            binding.professorView.visibility = View.VISIBLE
        }
    }
}