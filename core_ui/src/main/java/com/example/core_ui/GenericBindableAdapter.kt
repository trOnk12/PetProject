package com.example.core_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class GenericBindableAdapter<T, R : ViewDataBinding>(var itemLayoutViewId: Int, var variableId: Int) :
    RecyclerView.Adapter<GenericBindableAdapter<T, R>.BindableViewHolder<T>>(), BindableAdapter<T> {

    interface OnItemClickListener {
        fun onItemClick(item: Any?)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    var dataList: List<T> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: R = DataBindingUtil.inflate(layoutInflater, itemLayoutViewId, parent, false) as R

        return BindableViewHolder(binding, variableId)
    }

    override fun onBindViewHolder(holder: BindableViewHolder<T>, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    override fun setData(data: List<T>?) {
        data?.let {
            dataList = data
            notifyDataSetChanged()
        }
    }

    inner class BindableViewHolder<T>(var binding: ViewDataBinding, var variableId: Int) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.apply {
                setVariable(variableId, item)
                executePendingBindings()
                root.setOnClickListener {
                    onItemClickListener?.onItemClick(item)
                }
            }
        }
    }
}

interface BindableAdapter<T> {
    fun setData(data: List<T>?)
}
