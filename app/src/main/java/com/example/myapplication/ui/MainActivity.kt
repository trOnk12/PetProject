package com.example.myapplication.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.domain.model.User
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : DaggerAppCompatActivity() {
    companion object {
        fun callingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        //Set up the controller only on first creation.
        if (savedInstanceState == null) {
            val navController = findNavController(R.id.nav_host)
            navigation.setupWithNavController(navController)
        }

    }

}