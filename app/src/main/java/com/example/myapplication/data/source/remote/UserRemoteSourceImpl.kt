package com.example.myapplication.data.source.remote

import android.net.Uri
import com.example.core.functional.Result
import com.example.myapplication.core.extension.mapToDomain
import com.example.myapplication.data.firebase.Authenticator
import com.example.myapplication.data.firebase.FireBaseStorage
import com.example.myapplication.data.firebase.FireStoreUserDataSource
import com.example.myapplication.data.source.UserRemoteSource
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.usecase.LoginData
import com.example.myapplication.domain.usecase.RegisterData
import java.lang.Exception
import java.lang.IllegalStateException
import javax.inject.Inject

class UserRemoteSourceImpl @Inject constructor(
    private val authenticator: Authenticator,
    private val fireStoreUserDataSource: FireStoreUserDataSource,
    private val fireBaseStorage: FireBaseStorage
) : UserRemoteSource {

    override suspend fun create(user: User): User {
        return when (val result = fireStoreUserDataSource.create(user)) {
            is Result.Success -> result.data
            is Result.Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }

    override fun isSignIn(): Boolean {
        return authenticator.isUserSignedIn()
    }

    override suspend fun get(userId: String): User {
        return when (val result = fireStoreUserDataSource.get(userId)) {
            is Result.Success -> result.data
            is Result.Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }

    override suspend fun uploadProfilePicture(user: User, uri: Uri): Result<Uri> {
        return try {
            val profilePictureUri = fireBaseStorage.uploadFile(uri, user.id)
            Result.Success(profilePictureUri)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    override suspend fun update(user: User): User {
        return when (val result = fireStoreUserDataSource.update(user)) {
            is Result.Success -> result.data
            is Result.Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }
}
