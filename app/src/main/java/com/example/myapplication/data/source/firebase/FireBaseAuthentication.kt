package com.example.myapplication.data.source.firebase

import com.example.core.functional.Result
import com.example.myapplication.core.extension.mapToDomain
import com.example.myapplication.data.firebase.FireBaseAuthenticator
import com.example.myapplication.domain.model.User

class FireBaseAuthentication(private val fireBaseAuthenticator: FireBaseAuthenticator) {

    suspend fun signIn(
        email: String,
        password: String
    ): User {
        return when (val result = fireBaseAuthenticator.signInWithEmailAndPassword(email, password)) {
            is Result.Success -> {
                result.data.mapToDomain()
            }
            is Result.Error -> throw(result.exception)
            else -> throw(IllegalStateException("Something went wrong"))
        }
    }

    fun isSignIn(): Boolean {
        return fireBaseAuthenticator.isUserSignedIn()
    }

}
