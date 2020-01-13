package com.example.myapplication.core.animator

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.cardview.widget.CardView

open class CardViewAnimatorHelper(
    private val cardView: CardView,
    private val startWidth: Float = cardView.width.toFloat(),
    private val endWidth: Float = -1f,
    private val startHeight: Float = cardView.height.toFloat(),
    private val endHeight: Float = -1f,
    private val startX: Float = cardView.x,
    private val startY: Float = cardView.y,
    private val endX: Float = -1f,
    private val endY: Float = -1f,
    private val startRadius: Float = cardView.radius,
    private val endRadius: Float = -1f,
    private val startElevation: Float = cardView.elevation,
    private val endElevation: Float = -1f,
    private val isArcPath: Boolean = false,
    private val duration: Long = 300L,
    private val interpolator: TimeInterpolator = AccelerateDecelerateInterpolator()
) {

    var progress: Float = 0f
        set(value) {
            field = value

            if (!isArcPath) {
                if (endWidth >= 0) cardView.layoutParams.width =
                    getProgressValue(startWidth, endWidth, progress).toInt()
                if (endHeight >= 0) cardView.layoutParams.height =
                    getProgressValue(startHeight, endHeight, progress).toInt()
                if (endWidth >= 0 || endHeight >= 0) cardView.requestLayout()

                if (endX >= 0) cardView.x = getProgressValue(startX, endX, progress)
                if (endY >= 0) cardView.y = getProgressValue(startY, endY, progress)

            } else {
                val arcMetric = ArcMetric.evaluate(startX, startY, endX, endY, 90f, Side.LEFT)
                val degree = arcMetric.getDegree(progress).toDouble()
                cardView.x = arcMetric.axisPoint.x + arcMetric.mRadius * ArcUtils.cos(degree)
                cardView.y = arcMetric.axisPoint.y - arcMetric.mRadius * ArcUtils.sin(degree)
            }
            if (endRadius >= 0) cardView.radius = getProgressValue(startRadius, endRadius, progress)
            if (endElevation >= 0) cardView.cardElevation =
                getProgressValue(startElevation, endElevation, progress)
        }

    fun getAnimator(
        forward: Boolean = true,
        updateListener: ((progress: Float) -> Unit)? = null
    ): ValueAnimator {
        val a =
            if (forward) ValueAnimator.ofFloat(0f, 1f)
            else ValueAnimator.ofFloat(1f, 0f)
        a.addUpdateListener {
            val progress = (it.animatedValue as Float)
            this.progress = progress
            updateListener?.invoke(progress)
        }
        a.duration = duration
        a.interpolator = interpolator
        return a
    }

    private fun getProgressValue(start: Float, end: Float, progress: Float): Float =
        start + (end - start) * progress

}