package com.example.myapplication.data.repository

import com.example.core.functional.Result
import com.example.core.functional.Result.Error
import com.example.myapplication.data.source.UserLocalSource
import com.example.myapplication.data.source.UserRemoteSource
import com.example.myapplication.domain.model.LoginData
import com.example.myapplication.domain.model.RegisterData
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteSource: UserRemoteSource,
    private val userLocalSource: UserLocalSource
) : UserRepository {


    override suspend fun register(registerData: RegisterData): User {
        when (val result = userRemoteSource.register(registerData)) {
            is Result.Success -> return result.data
            is Error -> throw result.exception
            else -> throw IllegalStateException("Illegal state")
        }
    }

    override suspend fun logIn(loginData: LoginData): User {
        if (isSignIn()) throw Exception("User already signed in")

        when (val result = userRemoteSource.signIn(loginData)) {
            is Result.Success -> {
                userLocalSource.catchUserId(result.data.id)
                return result.data
            }
            is Error -> throw result.exception
            else -> throw IllegalStateException("Illegal state")
        }
    }

    override suspend fun getUser(id: String): User {
        return when (val result = userRemoteSource.getUser(id)) {
            is Result.Success -> result.data
            is Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }

    override fun isSignIn(): Boolean {
        return userRemoteSource.isSignIn()
    }

}