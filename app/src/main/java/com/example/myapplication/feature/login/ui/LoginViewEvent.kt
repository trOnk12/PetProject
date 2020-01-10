package com.example.myapplication.feature.login.ui

import com.example.myapplication.data.util.ValidationError

sealed class LoginViewEvent {
    object OpenMainActivity : LoginViewEvent()
    data class ShowPasswordError(val error: ValidationError) : LoginViewEvent()
    data class ShowEmailError(val error: ValidationError) : LoginViewEvent()
    data class ShowSnackBarMessage(val message: String) : LoginViewEvent()
}
