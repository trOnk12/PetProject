package com.example.myapplication.feature.splash

sealed class SplashViewEvent {
    object OpenMainActivity : SplashViewEvent()
    object OpenLoginFragment : SplashViewEvent()
}
