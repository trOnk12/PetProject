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
        return when (val user = fireBaseAuthenticator.signInWithEmailAndPassword(email, password)) {
            is Result.Success -> {
                user.data.mapToDomain()
            }
            is Result.Error -> throw(user.exception)
            else -> throw(IllegalStateException("Something went wrong"))
        }
    }

    fun isSignIn(): Boolean {
        return fireBaseAuthenticator.isUserSignedIn()
    }

}
