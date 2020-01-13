package com.example.myapplication.core.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.ContextThemeWrapper

class DisplayMetricsWrapper(val activity: Activity) {
    private val displayMetrics: DisplayMetrics = DisplayMetrics()

    init {
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
    }

    val screenMiddleWidth: Float = (displayMetrics.widthPixels / 2).toFloat()

    val screenMiddleHeight: Float = (displayMetrics.heightPixels / 2).toFloat()

}