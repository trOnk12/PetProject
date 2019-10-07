package com.example.core.network.data

sealed class Outcome<out T:Any> {
    data class Success<out T:Any>(val value: T) : Outcome<T>()
    data class Failure(val message: String, val exception: Exception) : Outcome<Nothing>()
}