package com.example.myapplication.core.extensions

fun Pair<String?, String?>.let(action: (pair: Pair<String?, String?>) -> Unit) {
    if (this.first != null && this.second != null) {
        action(this)
    }
}
