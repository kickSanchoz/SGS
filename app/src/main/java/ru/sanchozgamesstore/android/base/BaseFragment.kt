package ru.sanchozgamesstore.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    private var mViewBinding: VB? = null
    val binding get() = mViewBinding!!

    abstract fun getLayoutID(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    open fun parseArguments() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mViewBinding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false)
        setUpViews()
        observeView()
        observeData()
        return binding.root
    }

    open fun setUpViews() {}

    open fun observeView() {}

    open fun observeData() {}

    override fun onDestroyView() {
        super.onDestroyView()
        mViewBinding = null
    }
}