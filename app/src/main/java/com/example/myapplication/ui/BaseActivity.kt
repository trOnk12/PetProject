package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.di.injectFeature

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeature()

    }

    fun showSnackbar(message: String) {
        // Snackbar.make(this,message,Snackbar.LENGTH_LONG)
    }

}