package com.example.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.BR
import java.util.Collections.emptyList

interface BindableAdapter<T> {
    fun setData(data: List<T>?)
}

open class GenericBindableAdapter<T, R : ViewDataBinding>(var itemView:Int) : RecyclerView.Adapter<BindableViewHolder<T>>(), BindableAdapter<T> {

     var dataList: List<T> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: R = DataBindingUtil.inflate(layoutInflater,itemView, parent, false) as R

        return BindableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindableViewHolder<T>, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    override fun setData(data: List<T>?) {
        data?.let{
            dataList = data
            notifyDataSetChanged()
        }
    }

}

class BindableViewHolder<T>(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        binding.apply {
            setVariable(BR.item, item)
            executePendingBindings()
        }
    }
}


