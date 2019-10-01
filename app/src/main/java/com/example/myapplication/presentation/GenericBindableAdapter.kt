package com.example.myapplication.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.BR

interface BindableAdapter<T> {
    fun setData(data: List<T>)
}

class BindableViewHolder<T>(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        binding.apply {
            setVariable(BR.item, item)
            executePendingBindings()
        }
    }
}

open class GenericBindableAdapter<T, R : ViewDataBinding> : RecyclerView.Adapter<BindableViewHolder<T>>(), BindableAdapter<T> {

    lateinit var dataList: List<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: R = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)

        return BindableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindableViewHolder<T>, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    override fun setData(data: List<T>) {
        dataList = data
    }

}

