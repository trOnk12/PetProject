package com.example.myapplication.domain.authentication

import com.example.myapplication.domain.usecase.LoginData
import com.example.myapplication.domain.usecase.RegisterData

class AuthenticationService(
    private val authenticationProvider: AuthenticationProvider
) {

    suspend fun login(loginData: LoginData) =
        authenticationProvider.login(loginData)


    suspend fun register(registerData: RegisterData) =
        authenticationProvider.register(registerData)

}