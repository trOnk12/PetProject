package com.example.myapplication.domain.usecase

import com.example.core.interactor.UseCase
import com.example.myapplication.data.repository.UserSessionRepository
import com.example.myapplication.domain.authentication.AuthenticationProviderFactory
import com.example.myapplication.domain.authentication.AuthenticationSource
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.entity.UserSession
import javax.inject.Inject

class LogInUseCase
@Inject constructor(
    private val authenticationProviderFactory: AuthenticationProviderFactory,
    private val userSessionRepository: UserSessionRepository
) : UseCase<User, LoginData>() {

    override suspend fun run(params: LoginData): User {
        if (params.source == null) throw Exception("No source chosen")

        return authenticationProviderFactory.create(params.source).login(params).also { user ->
            userSessionRepository.create(mapToUserSession(user))
        }
    }

    private fun mapToUserSession(user: User): UserSession {
        return UserSession(id = user.id)
    }
}

data class LoginData(
    val source: AuthenticationSource? = null,
    var password: String = "",
    var email: String = ""
)
