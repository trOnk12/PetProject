package com.example.myapplication.ui

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("isRefreshing")
fun setIsRefreshing(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing:Boolean){
    swipeRefreshLayout.isRefreshing = isRefreshing
}
