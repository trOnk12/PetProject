package com.example.myapplication.data.source.user.remote

import com.example.core.functional.Result
import com.example.myapplication.data.firebase.FireStoreUserSource
import com.example.myapplication.data.source.RemoteSource
import com.example.myapplication.domain.entity.User
import javax.inject.Inject

class UserRemoteSource @Inject constructor(
    private val fireStoreUserSource: FireStoreUserSource
) : RemoteSource {

    override suspend fun create(user: User): User {
        return when (val result = fireStoreUserSource.create(user)) {
            is Result.Success -> result.data
            is Result.Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }

    override suspend fun get(userId: String): User {
        return when (val result = fireStoreUserSource.get(userId)) {
            is Result.Success -> result.data
            is Result.Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }

    override suspend fun update(user: User): User {
        return when (val result = fireStoreUserSource.update(user)) {
            is Result.Success -> result.data
            is Result.Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }
}
