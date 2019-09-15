package com.example.myapplication.domain.entity

sealed class Outcome<out T:Any> {
    data class Success<out T:Any>(val value: T) : Outcome<T>()
    data class Failure(val message: String, val exception: Exception) : Outcome<Nothing>()
    object Loading : Outcome<Nothing>()
}