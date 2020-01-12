package com.example.myapplication.domain.authentication

import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.usecase.LoginData
import com.example.myapplication.domain.usecase.RegisterData

interface Authentication {

    suspend fun login(loginData: LoginData): User

    suspend fun register(registerData: RegisterData): User
}

interface AuthenticationProviderFactory {
    fun create(source: AuthenticationSource): Authentication
}

interface AuthenticationSource


