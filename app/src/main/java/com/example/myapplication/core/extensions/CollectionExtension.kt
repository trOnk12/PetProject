package com.example.myapplication.core.extensions

fun <T> Iterable<T>.replace(old: T, new: T): List<T> {
    return map { if (it == old) new else it }
}
