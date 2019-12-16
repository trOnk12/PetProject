package com.example.myapplication.data.firebase



import com.example.core.functional.Result
import com.example.myapplication.domain.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class FireStoreUserDataSource(private val fireStore: FirebaseFirestore) {

    companion object {
        private const val USERS_COLLECTION = "users"
        internal const val ID = "id"
        internal const val NAME = "userName"
        internal const val FAVOURITE_COMMENTS = "favouriteComments"
    }

    suspend fun createUser(user: User): Result<FireStoreStatus> =
        withContext(Dispatchers.Main) {
            suspendCancellableCoroutine<Result<FireStoreStatus>> { continuation ->

                val data = mapOf(
                    ID to user.id,
                    NAME to user.name,
                    FAVOURITE_COMMENTS to user.favouriteComment
                )
                fireStore.collection(USERS_COLLECTION)
                    .document(user.id)
                    .set(data, SetOptions.merge())
                    .addOnCompleteListener {
                        if (!continuation.isActive) return@addOnCompleteListener
                        if (it.isSuccessful) {
                            continuation.resume(
                                Result.Success(FireStoreStatus.SUCCESS)
                            )
                        } else {
                            it.exception?.let {
                                continuation.resume(
                                    Result.Error(it)
                                )
                            }
                        }
                    }
                    .addOnFailureListener {
                        if (!continuation.isActive) return@addOnFailureListener
                        continuation.resumeWithException(it)
                    }

            }
        }

    suspend fun getUser(id: String): Result<User> =
        withContext(Dispatchers.Main) {
            suspendCancellableCoroutine<Result<User>> { continuation ->
                fireStore.collection(USERS_COLLECTION)
                    .document(id)
                    .get()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val result = it.result
                            val user = result?.data
                        } else {
                            it.exception?.let {
                                continuation.resume(Result.Error(it))
                            }
                        }
                    }
            }
        }
}

enum class FireStoreStatus {
    SUCCESS
}

