package com.example.myapplication.core.animator

import android.view.View

object ArcUtils {

    fun sin(degree: Double): Float {
        return kotlin.math.sin(Math.toRadians(degree)).toFloat()
    }

    fun cos(degree: Double): Float {
        return kotlin.math.cos(Math.toRadians(degree)).toFloat()
    }

    fun asin(value: Double): Float {
        return Math.toDegrees(kotlin.math.asin(value)).toFloat()
    }

    internal fun acos(value: Double): Float {
        return Math.toDegrees(kotlin.math.acos(value)).toFloat()
    }

    internal fun centerX(view: View): Float {
        return view.x + view.width / 2f
    }

    internal fun centerY(view: View): Float {
        return view.y + view.height / 2f
    }
}