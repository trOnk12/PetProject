package com.example.core.functional


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    data class CustomError<out T>(val error: T) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is CustomError<*> -> "Custom Error"
            Loading -> "Loading"
        }
    }
}

/**
 * [Success.data] if [Result] is of type [Success]
 */
fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data


