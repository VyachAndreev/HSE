package com.andreev.lessons_flow.ui.lessons

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.RoomDatabase
import com.andreev.core.base.BaseFragment
import com.andreev.core.di.ApplicationComponent
import com.andreev.data.db.DAO
import com.andreev.data.utils.DateUtils
import com.andreev.data.models.Lesson
import com.andreev.data.db.LessonDatabase
import com.andreev.lessons_flow.R
import com.andreev.lessons_flow.databinding.FragmentLessonsBinding
import com.andreev.lessons_flow.ui.Constants
import com.andreev.lessons_flow.ui._adapters.LessonAdapter
import com.andreev.lessons_flow.ui._adapters.VerticalSpaceDecoration
import com.andreev.lessons_flow.ui.lesson_info.LessonInfoFragment
import timber.log.Timber
import java.util.*

class LessonsFragment : BaseFragment<FragmentLessonsBinding>() {
    private lateinit var viewModel: LessonsViewModel
    private val adapter by lazy { LessonAdapter(arrayOf()) }
    private var db: LessonDatabase? = null
    private var dao: DAO? = null
    private val currentDate = Calendar.getInstance().time

    override fun getLayoutRes(): Int = R.layout.fragment_lessons

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            swipeLayout.isRefreshing = true
            with(recycler) {
                layoutManager = LinearLayoutManager(context)
                adapter = this@LessonsFragment.adapter
                addItemDecoration(VerticalSpaceDecoration(2))
            }
            adapter.onItemClick = { id ->
                launchFragment(
                    fragment = LessonInfoFragment(),
                    replace = true,
                    addToStack = true,
                    extras = Bundle().apply { putString(Constants.lessonId, id) }
                )
            }

            swipeLayout.setOnRefreshListener { viewModel.getLessons() }
        }
        with(viewModel) {
            errorMessage.observe(viewLifecycleOwner, errorMessageObserver)
            lessons.observe(viewLifecycleOwner, lessonsObserver)
            getLessons()
        }
        db = activity?.let { LessonDatabase.getLessonDatabase(it) }
        dao = db?.dao()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        viewModel = ViewModelProvider(this).get(LessonsViewModel::class.java)
        viewModel.injectDependencies(applicationComponent)
    }

    private fun saveLessonsToDataBase(lessons: Array<Lesson>) {
        lessons.forEach {
            if (
                DateUtils.formatSimpleDate(it.date_start) == DateUtils.formatSimpleDate(currentDate)
            ) {
                dao?.insertLesson(it)
                Timber.i("lessons from db:")
                dao?.getLessons()?.forEach { lesson ->
                    Timber.i("$lesson")
                }
            } else {
                Timber.i("dao is null")
            }
        }
    }

    private val lessonsObserver = Observer<Array<Lesson>> {
        saveLessonsToDataBase(it)
        binding.swipeLayout.isRefreshing = false
        if (it.isNotEmpty()) {
            adapter.lessons = it
            Timber.i(
                """GET: /lessons
                    |${
                    it.joinToString { lesson ->
                        "${lesson}\n"
                    }
                }
                """.trimMargin()
            )
            adapter.lessons.indices.forEach { index ->
                adapter.notifyItemChanged(index)
            }
        } else {
            showToast(R.string.nothing_found)
        }
    }

    override val errorMessageObserver: Observer<Int>
        get() {
            binding.swipeLayout.isRefreshing = false
            return super.errorMessageObserver
        }
}