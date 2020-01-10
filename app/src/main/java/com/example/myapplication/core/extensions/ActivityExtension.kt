package com.example.myapplication.core.extensions

import android.app.Activity
import android.content.Intent
import android.widget.Toast

fun Activity.startWithFinish(intent: Intent) {
    startActivity(intent)
    finish()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
