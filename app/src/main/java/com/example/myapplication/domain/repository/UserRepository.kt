package com.example.myapplication.domain.repository

import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.usecase.LoginData
import com.example.myapplication.domain.usecase.RegisterData

interface UserRepository {
    suspend fun logIn(loginData: LoginData): User
    suspend fun register(registerData: RegisterData): User
    suspend fun getUser(id: String): User
    suspend fun getUser(): User
    suspend fun updateUser(user: User): User
    suspend fun uploadProfilePicture(uri: String): User
    suspend fun addCommentToFavourite(comment: Comment): Comment
    fun isUserSignIn(): Boolean
}