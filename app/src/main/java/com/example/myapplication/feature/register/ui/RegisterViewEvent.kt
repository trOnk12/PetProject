package com.example.myapplication.feature.register.ui

import com.example.myapplication.data.util.ValidationError

sealed class RegisterViewEvent {
    data class ShowPasswordError(val error: ValidationError) : RegisterViewEvent()
    data class ShowEmailError(val error: ValidationError) : RegisterViewEvent()
    data class ShowRepeatPasswordError(val error: ValidationError) : RegisterViewEvent()
    data class ShowSnackBarMessage(val message: String) : RegisterViewEvent()
}