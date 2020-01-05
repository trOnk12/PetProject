package com.example.myapplication.core

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide

@BindingAdapter("isRefreshing")
fun setIsRefreshing(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing: Boolean) {
    swipeRefreshLayout.isRefreshing = isRefreshing
}

@BindingAdapter("profileImage")
fun setProfileImage(imageView: ImageView, uri: String?) {
    uri?.let {
        Glide.with(imageView)
            .load(uri.toUri())
            .centerCrop()
            .into(imageView)
    }
}
