package com.example.core_ui

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

abstract class GenericBindableAdapter<T> :
    RecyclerView.Adapter<BindableViewHolder<T>>() {

    var dataList: List<T> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        updateList(oldValue, newValue)
    }

    abstract fun diffCallBack(oldValue: List<T>, newValue: List<T>): DiffUtil.Callback

    private fun updateList(oldValue: List<T>, newValue: List<T>) {
        val diffResult = DiffUtil.calculateDiff(diffCallBack(oldValue, newValue))
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: BindableViewHolder<T>, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<T>

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
