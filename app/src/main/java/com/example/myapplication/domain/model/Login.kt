package com.example.myapplication.domain.model

data class LoginData(val password: Password, val email: Email) {
    data class Password(val value: String, var isValid: Boolean = false)
    data class Email(val value: String, var isValid: Boolean = false)
}
