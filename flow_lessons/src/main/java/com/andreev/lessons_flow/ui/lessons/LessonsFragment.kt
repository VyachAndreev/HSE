package com.andreev.lessons_flow.ui.lessons

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreev.core.base.BaseFragment
import com.andreev.core.di.ApplicationComponent
import com.andreev.data.models.Lesson
import com.andreev.lessons_flow.R
import com.andreev.lessons_flow.databinding.FragmentLessonsBinding
import com.andreev.lessons_flow.ui._adapters.LessonAdapter
import timber.log.Timber

class LessonsFragment: BaseFragment<FragmentLessonsBinding>() {
    private lateinit var viewModel: LessonsViewModel
    private val adapter by lazy { LessonAdapter(arrayOf()) }

    override fun getLayoutRes(): Int = R.layout.fragment_lessons

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.lessons.observe(viewLifecycleOwner, lessonsObserver)
        with(binding.recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@LessonsFragment.adapter
        }
        viewModel.getLessons()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        viewModel = ViewModelProvider(this).get(LessonsViewModel::class.java)
        viewModel.injectDependencies(applicationComponent)
    }

    private val lessonsObserver = Observer<Array<Lesson>> {
        if (it.isNotEmpty()) {
            adapter.lessons = it
            Timber.i("""GET: /lessons
                    |${it.joinToString { lesson ->
                "${lesson}\n"
            }}
                """.trimMargin())
            adapter.lessons.indices.forEach { index ->
                adapter.notifyItemChanged(index)
            }
        } else {
            showToast(R.string.nothing_found)
        }
    }
}