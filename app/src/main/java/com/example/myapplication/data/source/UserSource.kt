package com.example.myapplication.data.source

import com.example.core.functional.Result
import com.example.myapplication.domain.model.LoginData
import com.example.myapplication.domain.model.RegisterData
import com.example.myapplication.domain.model.User

interface UserRemoteSource {
    suspend fun register(registerData: RegisterData): Result<User>
    suspend fun signIn(loginData: LoginData): Result<User>
    suspend fun getUser(userId: String): Result<User>
    suspend fun updateUser(user: User)
    fun isSignIn(): Boolean
}

interface UserLocalSource {
    fun cacheUserId(data: String)
    fun getUserId(): String?
}