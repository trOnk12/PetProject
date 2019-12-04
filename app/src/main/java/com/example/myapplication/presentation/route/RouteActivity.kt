package com.example.myapplication.presentation.route

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RouteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Navigator.showMain(this)
    }

}