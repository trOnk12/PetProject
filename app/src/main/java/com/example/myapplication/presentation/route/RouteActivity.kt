package com.example.myapplication.presentation.route

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.di.injectFeature
import org.koin.android.ext.android.inject

class RouteActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeature()
        navigator.showMain(this)
    }

}