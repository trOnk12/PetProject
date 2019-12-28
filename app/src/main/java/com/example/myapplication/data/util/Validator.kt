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

    fun validatePasswordWithRepeat(
        password: String,
        repeatPassword: String,
        onError: (ValidationError) -> Unit,
        onRepeatError: (ValidationError) -> Unit
    ): Boolean {
        if (!password.equals(repeatPassword, true)) {
            onError(ValidationError.PasswordIsNotEqual)
            return false
        }

        validatePassword(password, onError)
        validatePassword(repeatPassword, onRepeatError)

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
    object PasswordIsNotEqual : ValidationError()
}
