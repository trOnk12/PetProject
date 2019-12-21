package com.example.myapplication.core.extension

import androidx.lifecycle.LiveData

fun LiveData<String>.valueOrEmpty(): String {
    value?.let {
        return it
    }
    return ""
}