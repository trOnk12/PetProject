package com.example.myapplication.data.source.remote

import com.example.myapplication.data.firebase.Authenticator
import com.example.myapplication.data.firebase.UserFireStore
import com.example.myapplication.data.source.UserRemoteSource
import com.example.myapplication.domain.model.LoginData
import  com.example.core.functional.Result
import com.example.myapplication.core.extension.mapToDomain
import com.example.myapplication.domain.model.RegisterData
import com.example.myapplication.domain.model.User
import java.lang.IllegalStateException

class UserRemoteSourceImpl(
    private val authenticator: Authenticator,
    private val userFireStore: UserFireStore
) : UserRemoteSource {

    override fun isSignIn(): Boolean {
        return authenticator.isUserSignedIn()
    }

    override suspend fun register(registerData: RegisterData): Result<User> {
        val registerResult = authenticator.register(registerData.email, registerData.password)

        return when (registerResult) {
            is Result.Success -> userFireStore.createUser(registerResult.data.mapToDomain())
            is Result.Error -> registerResult
            else -> throw IllegalStateException("Invalid state")
        }
    }

    override suspend fun signIn(loginData: LoginData): Result<User> {
        val loginResult = authenticator.logIn(loginData.email, loginData.password)

        return when (loginResult) {
            is Result.Success -> Result.Success(loginResult.data.mapToDomain())
            is Result.Error -> loginResult
            else -> throw IllegalStateException("Invalid state")
        }
    }

    override suspend fun getUser(userId: String): Result<User> {
        return userFireStore.getUser(userId)
    }

    override suspend fun updateUser(user: User) {
        userFireStore.updateUser(user)
    }

}