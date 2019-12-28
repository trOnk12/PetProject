package com.example.myapplication.domain.usecase

import com.example.core.interactor.UseCase
import com.example.myapplication.domain.model.LoginData
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class LogInUseCase
@Inject constructor(
    private val userRepository: UserRepository
) : UseCase<User, LoginData>() {

    override suspend fun run(params: LoginData): User {
        return userRepository.logIn(params)
    }

}
