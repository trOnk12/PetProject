package com.example.myapplication.data.repository

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.myapplication.data.firebase.FireStoreUserDataSource
import com.example.myapplication.data.source.firebase.UserFireBaseSource
import com.example.myapplication.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userFireBaseSource: UserFireBaseSource,
    private val fireStoreUserDataSource: FireStoreUserDataSource
) : UserRepository {

    override fun isUserSignIn(): Boolean {
        return userFireBaseSource.isUserSignIn()
    }

    //check if user logged in
    //if yes -> retrieve user data -> go to main activity
    //if no -> go to log in activity
    override suspend fun signIn(email: String, password: String): Either<Failure, Any> {
        val user = userFireBaseSource.signIn(email,password)

        if(user.isRight) fireStoreUserDataSource.createUser(user.)

    }


}