package com.example.myapplication.core.extension

fun <T> Iterable<T>.replace(old: T, new: T) {
    map { if (it == old) new else it }
}
