package com.example.myapplication.presentation.toolbar

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.example.myapplication.presentation.search.SearchActivity
import kotlinx.android.synthetic.main.toolbar.view.*

class ToolBar(context: Context, attrs: AttributeSet) : Toolbar(context, attrs) {

    private var hostActivity: Activity? = null
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    init {
        inflater.inflate(R.layout.toolbar, this, true).apply {
            search_icon.setOnClickListener { openSearchActivity() }
        }
    }

    fun attachHostActivity(activity: Activity) {
        if (hostActivity == null) {
            hostActivity = activity
        }
    }

    private fun openSearchActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions
                .makeSceneTransitionAnimation(hostActivity)
            context.startActivity(SearchActivity.callingIntent(context), options.toBundle())
        } else {
            context.startActivity(SearchActivity.callingIntent(context))
        }
    }

}