package com.example.core_ui

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

abstract class GenericBindableAdapter<T> :
    RecyclerView.Adapter<BindableViewHolder<T>>() {

    private var dataList: List<T> = emptyList()

    private fun setData(newValue: List<T>) {
        dataList = newValue
    }

    private fun clearData() {
        (dataList as? ArrayList)?.clear()
    }

    fun updateData(newValue: List<T>, result: DiffUtil.DiffResult) {
        result.dispatchUpdatesTo(this)

        clearData()
        setData(newValue)
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
