package com.example.myapplication.domain.authentication

import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.usecase.LoginData
import com.example.myapplication.domain.usecase.RegisterData

interface AuthenticationProvider {

    suspend fun login(loginData: LoginData): User

    suspend fun register(registerData: RegisterData): User
}

abstract class AuthenticationProviderFactory {
    abstract fun create(source: AuthenticationSource): AuthenticationProvider
}

enum class AuthenticationSource {
    Default, FireBase
}
