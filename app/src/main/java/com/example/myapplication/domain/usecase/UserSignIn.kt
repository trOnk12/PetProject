package com.example.myapplication.domain.usecase

import com.example.core.functional.Result
import com.example.core.interactor.UseCase
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository

class UserSignIn(private val userRepository: UserRepository) :
    UseCase<User, Map<CredentialsParameters, String>>() {

    override suspend fun run(params: Map<CredentialsParameters, String>): Result<User> {
        val email = params[CredentialsParameters.EMAIL]
        val password = params[CredentialsParameters.PASSWORD]

        return userRepository.signIn(email!!, password!!)
    }
}

enum class CredentialsParameters {
    EMAIL,
    PASSWORD
}