package com.example.myapplication.ui.route

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.core_ui.platform.BaseActivity
import com.example.core_ui.platform.BaseFragment
import com.example.myapplication.di.injectFeature

class RouteActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeature()

        navigator.showMain()
    }

}