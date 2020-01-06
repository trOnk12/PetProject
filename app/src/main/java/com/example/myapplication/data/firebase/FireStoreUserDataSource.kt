package com.example.myapplication.data.firebase

import com.example.core.functional.Result
import com.example.myapplication.domain.entity.User
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

class FireStoreUserDataSource
@Inject constructor(
    private val fireStore: FirebaseFirestore
) {

    companion object {
        private const val USERS_COLLECTION = "users"
        internal const val ID = "id"
        internal const val NAME = "userName"
        internal const val FAVOURITE_COMMENTS = "favouriteCommentsId"
    }

    suspend fun create(user: User): Result<User> =
        withContext(Dispatchers.Main) {
            suspendCancellableCoroutine<Result<User>> { continuation ->

                val data = mapOf(
                    ID to user.id,
                    NAME to user.name,
                    FAVOURITE_COMMENTS to user.favouriteCommentsId
                )
                fireStore.collection(USERS_COLLECTION)
                    .document(user.id)
                    .set(data)
                    .addOnSuccessListener {
                        if (!continuation.isActive) return@addOnSuccessListener
                        continuation.resume(
                            Result.Success(user)
                        )
                    }
                    .addOnFailureListener {
                        if (!continuation.isActive) return@addOnFailureListener
                        continuation.resumeWithException(it)
                    }
            }
        }

    suspend fun get(id: String): Result<User> =
        withContext(Dispatchers.Main) {
            suspendCancellableCoroutine<Result<User>> { continuation ->
                fireStore.collection(USERS_COLLECTION)
                    .document(id)
                    .get()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val result = it.result
                            result?.let {
                                val user = result.toObject(User::class.java)
                                if (user == null) {
                                    continuation.resumeWithException(Exception("No userSession found"))
                                } else {
                                    continuation.resume(Result.Success(user))
                                }
                            }
                        } else {
                            it.exception?.let {
                                continuation.resume(Result.Error(it))
                            }
                        }
                    }
            }
        }

    suspend fun update(user: User): Result<User> =
        withContext(Dispatchers.IO) {
            suspendCancellableCoroutine<Result<User>> { continuation ->

                val data = mapOf(
                    ID to user.id,
                    NAME to user.name,
                    FAVOURITE_COMMENTS to user.favouriteCommentsId
                )
                fireStore.collection(USERS_COLLECTION)
                    .document(user.id)
                    .set(data)
                    .addOnSuccessListener {
                        if (!continuation.isActive) return@addOnSuccessListener
                        continuation.resume(
                            Result.Success(user)
                        )
                    }
                    .addOnFailureListener {
                        if (!continuation.isActive) return@addOnFailureListener
                        continuation.resumeWithException(it)
                    }
            }
        }
}
