package com.example.myapplication.data.source

import android.net.Uri
import com.example.core.functional.Result
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.usecase.LoginData
import com.example.myapplication.domain.usecase.RegisterData

interface UserRemoteSource {
    suspend fun register(registerData: RegisterData): Result<User>
    suspend fun signIn(loginData: LoginData): Result<User>
    suspend fun getUser(userId: String): Result<User>
    suspend fun updateUser(user: User): Result<User>
    suspend fun uploadProfilePicture(user: User, uri: Uri): Result<Uri>
    fun isSignIn(): Boolean
}

interface UserLocalSource {
    fun cacheUserId(data: String)
    fun getUserId(): String?
}
