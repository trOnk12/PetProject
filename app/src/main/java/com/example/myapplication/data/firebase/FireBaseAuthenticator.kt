package com.example.myapplication.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import com.example.core.functional.Result

class FireBaseAuthenticator(private val fireBaseAuth: FirebaseAuth) {

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Result<FirebaseUser> =
        withContext(Dispatchers.Main) {
            suspendCancellableCoroutine<Result<FirebaseUser>> { continuation ->
                fireBaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (!continuation.isActive) return@addOnCompleteListener
                        if (it.isSuccessful) {
                            val user = fireBaseAuth.currentUser
                            user?.let {
                                continuation.resume(Result.Success(it))
                            }
                        } else {
                            it.exception?.let {
                                continuation.resume(Result.Error(it))
                            }

                        }
                    }
            }
        }

    fun isUserSignedIn(): Boolean {
        return fireBaseAuth.currentUser != null
    }
}