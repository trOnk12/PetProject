package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.view.*

abstract class GenericAdapter<T> : RecyclerView.Adapter<BindableViewHolder<T>>() {

    var dataList = emptyList<T>()

    override fun onBindViewHolder(p0: BindableViewHolder<T>, p1: Int) {

    }

    abstract override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BindableViewHolder<T>

    override fun getItemCount(): Int {
        return dataList.size
    }

}


class BindableViewHolder<T>(var binding: ViewDataBinding, var variableId: Int) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        binding.apply {
            setVariable(variableId, item)
            executePendingBindings()
        }
    }
}