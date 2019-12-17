package com.example.myapplication.data.repository

import com.example.core.functional.Result
import com.example.core.functional.Result.Error
import com.example.myapplication.data.firebase.FireStoreUserDataSource
import com.example.myapplication.data.local.sharedpreferences.SharedPreferenceStorage
import com.example.myapplication.data.source.firebase.FireBaseAuthentication
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository

class UserRepositoryImpl(
    private val fireBaseAuthentication: FireBaseAuthentication,
    private val fireStoreUserDataSource: FireStoreUserDataSource,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : UserRepository {

    override suspend fun signIn(email: String, password: String): User {
        if (isSignIn()) throw Exception("User is already sign in")
        return fireBaseAuthentication.signIn(email, password)
    }

    private suspend fun successfulAuthentication(user: User) {
        sharedPreferenceStorage.userId = user.id
        fireStoreUserDataSource.createUser(user)
    }

    override suspend fun getUser(id: String): User {
        return if (isSignIn()) {
            fireStoreUserDataSource.getUser(id)
        } else {
            throw Exception("No user sign in")
        }
    }

    override fun isSignIn(): Boolean {
        return fireBaseAuthentication.isSignIn()
    }


}