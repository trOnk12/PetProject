package com.example.myapplication.core.extension

import androidx.lifecycle.MutableLiveData

fun <T> Iterable<T>.replace(old: T, new: T) {
    map { if (it == old) new else it }
}


