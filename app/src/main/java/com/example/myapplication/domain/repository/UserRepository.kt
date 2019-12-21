package com.example.myapplication.domain.repository

import com.example.core.functional.Result
import com.example.core.interactor.None
import com.example.myapplication.domain.model.LoginData
import com.example.myapplication.domain.model.User


interface UserRepository {
    suspend fun logIn(loginData:LoginData): User
    suspend fun getUser(id:String): User
    fun isSignIn(): Boolean
}