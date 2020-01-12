package com.example.myapplication.core.commons.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity(val layoutResId: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        initDependencyComponent()
    }

    abstract fun initDependencyComponent()
}


