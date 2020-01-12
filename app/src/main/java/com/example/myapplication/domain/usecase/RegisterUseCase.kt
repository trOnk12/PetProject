package com.example.myapplication.domain.usecase

import com.example.core.interactor.UseCase
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.domain.authentication.AuthenticationProvider
import com.example.myapplication.domain.authentication.AuthenticationProviderFactory
import com.example.myapplication.domain.authentication.AuthenticationSource
import com.example.myapplication.domain.entity.User
import javax.inject.Inject

class RegisterUseCase
@Inject constructor(
    private val authenticationProviderFactory: AuthenticationProviderFactory,
    private val userRepository: UserRepository
) : UseCase<User, RegisterData>() {

    override suspend fun run(params: RegisterData): User {
        if (params.source == null) throw Exception("No source chosen")

        return authenticationProviderFactory.create(params.source).register(params).also { user ->
            userRepository.create(user)
        }
    }
}

data class RegisterData(
    val source: AuthenticationSource? = null,
    var email: String = "",
    var password: String = "",
    var repeatPassword: String = "",
    var userName: String = "",
    var gender: String = ""
)
