package com.example.myapplication.domain.usecase

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.core.interactor.UseCase
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class SignIn
@Inject constructor(
    private val userRepository: UserRepository
) :
    UseCase<AuthenticationStatus, Pair<String, String>>() {
    override suspend fun run(params: Pair<String, String>): Result<AuthenticationStatus> {
        return userRepository.signIn(params.first, params.second)
    }
}