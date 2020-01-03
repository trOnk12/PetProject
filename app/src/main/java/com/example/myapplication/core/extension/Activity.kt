package com.example.myapplication.core.extension

import android.app.Activity
import android.content.Intent

fun Activity.startWithFinish(intent: Intent){
    startActivity(intent)
    finish()
}
