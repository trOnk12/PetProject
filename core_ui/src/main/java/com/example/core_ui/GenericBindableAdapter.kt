package com.example.core_ui

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class GenericBindableAdapter<T> :
    RecyclerView.Adapter<GenericBindableAdapter<T>.BindableViewHolder<T>>() {

    var dataList: List<T> = emptyList()

    fun setData(data: List<T>?) {
        data?.let {
            dataList = data
            notifyDataSetChanged()
        }
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<T>

    override fun onBindViewHolder(holder: BindableViewHolder<T>, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    inner class BindableViewHolder<T>(var binding: ViewDataBinding, var variableId: Int) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.apply {
                setVariable(variableId, item)
                executePendingBindings()
            }
        }
    }
}
