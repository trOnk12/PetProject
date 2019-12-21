package com.example.myapplication.data.source.remote

import com.example.myapplication.data.firebase.Authenticator
import com.example.myapplication.data.firebase.UserFireStore
import com.example.myapplication.data.source.UserRemoteSource
import com.example.myapplication.domain.model.LoginData
import  com.example.core.functional.Result
import com.example.myapplication.core.extension.mapToDomain
import com.example.myapplication.domain.model.User
import java.lang.IllegalStateException


class UserRemoteSourceImpl(
    private val authenticator: Authenticator,
    private val userFireStore: UserFireStore
) : UserRemoteSource {

    override suspend fun signIn(loginData: LoginData): Result<User> {
        val result = authenticator.signInWithEmailAndPassword(
            loginData.email.value,
            loginData.password.value
        )

        return when (result) {
            is Result.Success -> userFireStore.createUser(result.data.mapToDomain())
            is Result.Error -> throw Exception(result.exception)
            else -> throw IllegalStateException("Invalid state")
        }
    }

    override fun isSignIn(): Boolean {
        return authenticator.isUserSignedIn()
    }

}