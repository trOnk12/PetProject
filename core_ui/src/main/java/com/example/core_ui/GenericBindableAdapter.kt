package com.example.core_ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindableViewHolder<T>(var binding: ViewDataBinding, var variableId: Int) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        binding.apply {
            setVariable(variableId, item)
            executePendingBindings()
        }
    }
}

