package com.example.myapplication.presentation.toolbar

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.presentation.search.SearchActivity
import kotlinx.android.synthetic.main.toolbar.view.*
import android.view.animation.AccelerateInterpolator
import androidx.core.view.ViewCompat.animate
import android.R.attr.translationY
import com.example.myapplication.R

class Toolbar(context: Context, attrs: AttributeSet) : Toolbar(context, attrs) {

    private var hostActivity: Activity? = null
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var view: View

    init {
        view = inflater.inflate(R.layout.toolbar, this, true).apply {
            search_icon.setOnClickListener { openSearchActivity() }
        }
    }

    fun attachHostActivity(activity: Activity) {
        if (hostActivity == null) {
            hostActivity = activity
        }
    }

    private fun openSearchActivity() {
        if (hostActivity == null) {
            context.startActivity(SearchActivity.callingIntent(context))
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions
                .makeSceneTransitionAnimation(hostActivity)
            context.startActivity(SearchActivity.callingIntent(context), options.toBundle())
        } else {
            context.startActivity(SearchActivity.callingIntent(context))
        }
    }

    fun hide() {
        //view.animate().alpha(0f).start()
    }

    fun show() {
     //   view.animate()
       //     .alpha(0f)
    }

}