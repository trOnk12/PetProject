package com.example.core_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

class BindingHelper{
companion object{
    fun createBinding(layoutId: Int, fromView: ViewGroup) : ViewDataBinding {
        val layoutInflater = LayoutInflater.from(fromView.context)
       return DataBindingUtil.inflate(layoutInflater, layoutId, fromView, false)
    }
}
}