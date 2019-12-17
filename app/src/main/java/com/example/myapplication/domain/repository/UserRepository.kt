package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.User
import com.example.core.functional.Result

interface UserRepository {
    suspend fun signIn(email: String, password: String): User
    suspend fun getUser(id:String): User
    fun isSignIn(): Boolean
}