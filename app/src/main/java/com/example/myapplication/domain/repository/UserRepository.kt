package com.example.myapplication.domain.repository

import com.example.core.functional.Either

interface UserRepository {
    suspend fun signIn(email: String, password: String): Result<Nothing>
    fun isUserSignIn():Boolean
}