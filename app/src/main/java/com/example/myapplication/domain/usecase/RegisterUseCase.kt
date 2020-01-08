package com.example.myapplication.domain.usecase

import com.example.core.interactor.UseCase
import com.example.myapplication.domain.authentication.AuthenticationProvider
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUseCase
@Inject constructor(
    private val authenticationProvider: AuthenticationProvider,
    private val userRepository: UserRepository
) : UseCase<User, RegisterData>() {

    override suspend fun run(params: RegisterData): User {
        return authenticationProvider.register(params).also { user ->
            userRepository.create(user)
        }
    }

}

data class RegisterData(
    var email: String = "",
    var password: String = "",
    var repeatPassword: String = "",
    var userName: String = "",
    var gender: String = ""
)
