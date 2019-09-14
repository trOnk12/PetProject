package com.example.myapplication.domain.entity

sealed class Outcome<T> {
    data class Success<T>(val value: T) : Outcome<T>()
    data class Failure<T>(val message: String, val throwable: Throwable) : Outcome<T>()
}