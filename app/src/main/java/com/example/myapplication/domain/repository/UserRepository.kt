package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.LoginData
import com.example.myapplication.domain.model.RegisterData
import com.example.myapplication.domain.model.User

interface UserRepository {
    suspend fun logIn(loginData: LoginData): User
    suspend fun register(registerData: RegisterData): User
    suspend fun getUser(id: String): User
    suspend fun getCachedUser():User?
    suspend fun updateUser(user: User)
    suspend fun addCommentToFavourite(id: String): User
    fun isSignIn(): Boolean
}