package com.example.myapplication.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        //set up the controller only on first creation.
        if (savedInstanceState == null) {
            val navController = findNavController(R.id.nav_host)
            navigation.setupWithNavController(navController)
        }

    }

}