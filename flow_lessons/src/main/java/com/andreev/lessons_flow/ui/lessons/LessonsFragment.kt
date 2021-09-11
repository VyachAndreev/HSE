package com.andreev.lessons_flow.ui.lessons

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreev.core.base.BaseFragment
import com.andreev.core.di.ApplicationComponent
import com.andreev.data.db.DAO
import com.andreev.data.utils.DateUtils
import com.andreev.data.models.Lesson
import com.andreev.data.db.LessonDatabase
import com.andreev.lessons_flow.R
import com.andreev.lessons_flow.databinding.FragmentLessonsBinding
import com.andreev.lessons_flow.ui.Constants
import com.andreev.lessons_flow.ui._adapters.DayAdapter
import com.andreev.lessons_flow.ui.lesson_info.LessonInfoFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class LessonsFragment : BaseFragment<FragmentLessonsBinding>() {
    private lateinit var viewModel: LessonsViewModel
    private var _adapter = DayAdapter()
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
                adapter = _adapter
            }
            _adapter.onLessonClick = { id ->
               onLessonClicked(id)
            }
            swipeLayout.setOnRefreshListener {
                DateUtils.formatSimpleDateDay(currentDate)?.let {
                    if (context?.let { it1 -> isOnline(it1) } == true) {
                        viewModel.getLessons(it)
                    } else {
                        showToast(R.string.no_connection)
                        swipeLayout.isRefreshing = false
                    }
                }
            }
        }
        db = activity?.let { LessonDatabase.getLessonDatabase(it) }
        dao = db?.dao()
        Timber.i("activity: $activity. db: $db, dao: $dao")
        with(viewModel) {
            errorMessage.observe(viewLifecycleOwner, errorMessageObserver)
            lessons.observe(viewLifecycleOwner, lessonsObserver)
            DateUtils.formatSimpleDateDay(currentDate)?.let {
                Timber.i("date: $it")
                if (context?.let { it1 -> isOnline(it1) } == true) {
                    viewModel.getLessons(it)
                } else {
                    getLessonsFromDataBase()
                }
            }
        }
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        viewModel = ViewModelProvider(this).get(LessonsViewModel::class.java)
        viewModel.injectDependencies(applicationComponent)
    }

    private fun onLessonClicked(id: String?) {
        Timber.i(id)
        if (context?.let { isOnline(it) } == true) {
            launchFragment(
                fragment = LessonInfoFragment(),
                replace = true,
                addToStack = true,
                extras = Bundle().apply { putString(Constants.lessonId, id) }
            )
        } else {
            showToast(R.string.no_connection)
        }
    }

    private fun saveLessonsToDataBase(lessons: Array<Lesson>) {
        CoroutineScope(Dispatchers.Default).launch {
            lessons.forEach {
                if (DateUtils.formatSimpleDateDay(it.date_start) ==
                    DateUtils.formatSimpleDateDay(currentDate) ||
                    it.date_start < currentDate
                ) {
                    dao?.insertLesson(it)
                }
            }
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        } else {
            null
        }
        if (capabilities != null) {
            return true
        }
        return false
    }

    private fun getLessonsFromDataBase() {
        showToast(R.string.no_connection)
        CoroutineScope(Dispatchers.Default).launch {
            dao?.deleteUnusedLessons(DateUtils.getStartOfTheDay(Calendar.getInstance().time))
            viewModel.lessons.postValue(dao?.getLessons())
        }
    }


    private val lessonsObserver = Observer<Array<Lesson>> { lessons ->
        saveLessonsToDataBase(lessons)
        binding.swipeLayout.isRefreshing = false
        if (lessons.isNotEmpty()) {
            val mLessons = lessons.toList().groupBy { DateUtils.getStartOfTheDay(it.date_start) }
            _adapter = DayAdapter(mLessons)
            binding.recycler.adapter = _adapter
            _adapter.onLessonClick = {
                onLessonClicked(it)
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