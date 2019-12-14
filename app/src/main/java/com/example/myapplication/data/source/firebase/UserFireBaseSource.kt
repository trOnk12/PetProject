package com.example.myapplication.data.source.firebase

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.core.functional.flatMap
import com.example.core.functional.map
import com.example.myapplication.data.firebase.FireBaseAuthenticator
import com.example.myapplication.domain.model.User

class UserFireBaseSource(private val fireBaseAuthenticator: FireBaseAuthenticator) {

    suspend fun signIn(email: String, password: String): Either<Failure, User> {
        val fireBaseUser =
            fireBaseAuthenticator.signInWithEmailAndPassword(email, password)

        return fireBaseUser.map {
            User(
                it.providerId,
                it.displayName.toString()
            )
        }
    }

    fun isUserSignIn(): Boolean {
        return fireBaseAuthenticator.isUserSignedIn()
    }

}
