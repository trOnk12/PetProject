package com.example.myapplication.data.util

class InputValidator {

    fun validatePassword(
        password: String,
        failed: (ValidateStatus.PasswordStatus) -> Unit
    ): Boolean {
        if (password.isEmpty())
            failed(ValidateStatus.PasswordStatus.EmptyPassword)
        return true
    }

    fun validateEmail(
        email: String,
        failed: (ValidateStatus.EmailStatus) -> Unit
    ): Boolean {
        if (email.isEmpty())
            failed(ValidateStatus.EmailStatus.EmptyEmail)
        return true
    }

}

sealed class ValidateStatus {
    sealed class PasswordStatus {
        object TooShortPassword : PasswordStatus()
        object EmptyPassword : PasswordStatus()
    }

    sealed class EmailStatus {
        object InvalidEmail : EmailStatus()
        object EmptyEmail : EmailStatus()
    }
}