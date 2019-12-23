package com.example.myapplication.data.util

import javax.inject.Inject

class Validator
@Inject constructor() {

    fun validatePassword(value: String, onError: (ValidationError) -> Unit): Boolean {
        if (value.isEmpty()) {
            onError(ValidationError.IsEmpty)
            return false
        }

        return true
    }

    fun validateEmail(value: String, OnError: (ValidationError) -> Unit): Boolean {
        if (value.isEmpty()) {
            OnError(ValidationError.IsEmpty)
            return false
        }

        return true
    }

}

sealed class ValidationError {
    object IsEmpty : ValidationError()
}
