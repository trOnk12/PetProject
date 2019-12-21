package com.example.myapplication.data.repository

import com.example.core.functional.Result
import com.example.core.functional.Result.Error
import com.example.core.interactor.None
import com.example.myapplication.data.source.UserLocalSource
import com.example.myapplication.data.source.UserRemoteSource
import com.example.myapplication.domain.model.LoginData
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository
import java.lang.IllegalStateException

class UserRepositoryImpl(
    private val userRemoteSource: UserRemoteSource,
    private val userLocalSource: UserLocalSource
) : UserRepository {

    override suspend fun getUser(id: String): User {

    }

    override suspend fun logIn(loginData: LoginData): User {
        if (isSignIn()) throw Exception("User already signed in")

        when (val result = userRemoteSource.signIn(loginData)) {
            is Result.Success -> {
                userLocalSource.catchUserId(result.data.id)
                return result.data
            }
            is Error -> throw Exception(result.exception)
            else -> throw IllegalStateException("Illegal state")
        }
    }

    override fun isSignIn(): Boolean {
        return userRemoteSource.isSignIn()
    }

}