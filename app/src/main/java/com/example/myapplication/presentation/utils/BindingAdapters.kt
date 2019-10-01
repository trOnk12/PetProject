package com.example.myapplication.presentation.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.presentation.BindableAdapter


@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items:List<T>) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(items)
    }
}