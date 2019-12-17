package com.example.myapplication.data.repository

import com.example.core.functional.Result
import com.example.core.functional.Result.Error
import com.example.myapplication.data.firebase.FireStoreUserDataSource
import com.example.myapplication.data.local.sharedpreferences.SharedPreferenceStorage
import com.example.myapplication.data.source.firebase.FireBaseAuthentication
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository
import java.lang.IllegalStateException

class UserRepositoryImpl(
    private val fireBaseAuthentication: FireBaseAuthentication,
    private val fireStoreUserDataSource: FireStoreUserDataSource,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : UserRepository {

    override suspend fun signIn(email: String, password: String): User {
        if (isSignIn()) throw Exception("User is already sign in")
        val user = fireBaseAuthentication.signIn(email, password)

        saveId(user)

        return user
    }

    private fun saveId(user: User) {
        sharedPreferenceStorage.userId = user.id
    }

    override suspend fun getUser(id: String): User {
        if (isSignIn()) {
            when (val result = fireStoreUserDataSource.getUser(id)) {
                is Error -> throw(result.exception)
                is Result.Success -> return result.data
                else -> throw IllegalStateException("Something went wrong")
            }
        } else {
            throw Exception("No user sign in")
        }
    }

    override fun isSignIn(): Boolean {
        return fireBaseAuthentication.isSignIn()
    }

}