package com.andreev.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<T: ViewDataBinding>: Fragment() {
    protected lateinit var viewBinding: T

    private fun inflateView(inflater: LayoutInflater) {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            getLayoutRes(),
            null,
            false,
        )
        viewBinding.lifecycleOwner = this
    }

    protected abstract fun injectDependencies()

    protected abstract fun getLayoutRes(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflateView(inflater)
        return viewBinding.root
    }
}