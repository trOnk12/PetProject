package com.example.myapplication.domain.usecase

import com.example.core.interactor.UseCase
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUseCase
@Inject constructor(
    private val userRepository: UserRepository
) : UseCase<User, RegisterData>() {

    override suspend fun run(params: RegisterData): User {
        return userRepository.register(params)
    }

}

data class RegisterData(
    var email: String = "",
    var password: String = "",
    var repeatPassword: String = "",
    var userName: String = "",
    var gender: String = ""
)