package com.example.myapplication.domain.usecase

import com.example.core.interactor.UseCase
import com.example.myapplication.domain.entity.User
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

data class LoginData(
    var password: String = "",
    var email: String = ""
)
