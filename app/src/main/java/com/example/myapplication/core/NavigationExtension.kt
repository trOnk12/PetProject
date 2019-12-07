package com.example.myapplication.core

import android.app.Activity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Activity.setUpBottomNavigationWithNav(bottomNavigationView: BottomNavigationView, navId: Int) {
    bottomNavigationView.setupWithNavController(findNavController(navId))
}