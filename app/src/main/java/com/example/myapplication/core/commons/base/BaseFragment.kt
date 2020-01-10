package com.example.myapplication.core.commons.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel>(@LayoutRes private val layoutId: Int) : Fragment() {

    @Inject
    lateinit var provider: ViewModelProvider.Factory

    lateinit var viewModel: VM
    lateinit var viewBinding: B

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onInitDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitViewModel()
        onInitDataBinding()
    }

    abstract fun onInitDependency()

    abstract fun onInitViewModel()

    abstract fun onInitDataBinding()
}
