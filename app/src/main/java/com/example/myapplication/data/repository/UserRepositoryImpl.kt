package com.example.myapplication.data.repository

import com.example.core.functional.Result
import com.example.core.functional.Result.Error
import com.example.myapplication.data.firebase.FireStoreUserDataSource
import com.example.myapplication.data.source.firebase.FireBaseAuthentication
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository

class UserRepositoryImpl(
    private val fireBaseAuthentication: FireBaseAuthentication,
    private val fireStoreUserDataSource: FireStoreUserDataSource
) : UserRepository {

    override suspend fun signIn(email: String, password: String): Result<User> {
        if (isSignIn()) return Error(Exception("User is already sign in"))

        val user =
            fireBaseAuthentication.signIn(email, password)
        if (user is Result.Success) {
            fireStoreUserDataSource.createUser(user.data)
        }

        return user
    }

    override suspend fun getUser(id:String): Result<User> {
        return if (isSignIn()) {
            fireStoreUserDataSource.getUser(id)
        } else {
            Error(Exception("No user sign in"))
        }
    }

    override fun isSignIn(): Boolean {
        return fireBaseAuthentication.isSignIn()
    }


}