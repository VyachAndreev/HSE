package com.andreev.lessons_flow.ui.lessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andreev.lessons_flow.R
import timber.log.Timber

class LessonsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("123")
        return inflater.inflate(R.layout.fragment_lessons, container, false)
    }
}