package com.example.myapplication.data.source

import com.example.core.functional.Result
import com.example.myapplication.domain.model.LoginData
import com.example.myapplication.domain.model.User


interface UserRemoteSource {
    suspend fun signIn(loginData: LoginData): Result<User>
    suspend fun getUser(userId:String) : Result<User>
    fun isSignIn(): Boolean
}

interface UserLocalSource {
    fun catchUserId(data: String)
}