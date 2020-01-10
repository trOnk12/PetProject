package com.example.myapplication.data.util

import javax.inject.Inject

class InputValidator
@Inject constructor() {

    fun validatePassword(password: String, onError: (ValidationError) -> Unit): Boolean {
        if (password.isEmpty()) {
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

    fun validateEmail(email: String, OnError: (ValidationError) -> Unit): Boolean {
        if (email.isEmpty()) {
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
