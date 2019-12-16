package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.User
import com.example.core.functional.Result

interface UserRepository {
    suspend fun signIn(email: String, password: String): Result<User>
    fun isSignIn(): Boolean
    suspend fun getUser(id:String): Result<User>
}