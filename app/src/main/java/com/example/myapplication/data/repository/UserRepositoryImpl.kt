package com.example.myapplication.data.repository

import androidx.core.net.toUri
import com.example.core.functional.Result
import com.example.core.functional.Result.Error
import com.example.myapplication.data.source.UserLocalSource
import com.example.myapplication.data.source.UserRemoteSource
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.repository.UserRepository
import com.example.myapplication.domain.usecase.LoginData
import com.example.myapplication.domain.usecase.RegisterData

class UserRepositoryImpl(
    private val userRemoteSource: UserRemoteSource,
    private val userLocalSource: UserLocalSource
) : UserRepository {

    override suspend fun register(registerData: RegisterData): User {
        when (val result = userRemoteSource.register(registerData)) {
            is Result.Success -> return result.data
            is Error -> throw result.exception
            else -> throw IllegalStateException("Illegal state")
        }
    }

    override suspend fun logIn(loginData: LoginData): User {
        if (isUserSignIn()) throw Exception("User already signed in")

        when (val result = userRemoteSource.signIn(loginData)) {
            is Result.Success -> {
                userLocalSource.cacheUserId(result.data.id)
                return result.data
            }
            is Error -> throw result.exception
            else -> throw IllegalStateException("Illegal state")
        }
    }

    override fun isUserSignIn(): Boolean {
        return userRemoteSource.isSignIn()
    }

    override suspend fun getUser(id: String): User {
        return when (val result = userRemoteSource.getUser(id)) {
            is Result.Success -> result.data
            is Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }

    override suspend fun getUser(): User {
        userLocalSource.getUserId()?.let {
            return getUser(it)
        }

        throw Exception("No cached userSession found!")
    }

    override suspend fun uploadProfilePicture(uri: String): User {
        return when (val result = userRemoteSource.uploadProfilePicture(getUser(), uri.toUri())) {
            is Result.Success -> {
                val newUser = getUser().updateProfileImageUrl(result.data.toString())
                updateUser(newUser)
            }
            is Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }

        override suspend fun updateUser(user: User): User {
        return when (val result = userRemoteSource.updateUser(user)) {
            is Result.Success -> result.data
            is Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }

    override suspend fun addCommentToFavourite(comment: Comment): Comment {
        val user = getUser().addCommentToFavourite(comment.id)

        return when (val result = userRemoteSource.updateUser(user)) {
            is Result.Success -> comment.toggleIsFavourite()
            is Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }
}
