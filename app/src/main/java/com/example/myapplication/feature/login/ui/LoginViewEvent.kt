package com.example.myapplication.feature.login.ui

sealed class LoginViewEvent {
    object OpenMainActivity : LoginViewEvent()
}
