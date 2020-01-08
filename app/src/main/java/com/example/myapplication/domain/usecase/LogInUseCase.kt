package com.example.myapplication.domain.usecase

import com.example.core.interactor.UseCase
import com.example.myapplication.domain.authentication.AuthenticationProviderFactory
import com.example.myapplication.domain.authentication.AuthenticationProviderSource
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.entity.UserSession
import com.example.myapplication.domain.repository.UserSessionRepository
import javax.inject.Inject

class LogInUseCase
@Inject constructor(
    private val authenticationProviderFactory: AuthenticationProviderFactory,
    private val userSessionRepository: UserSessionRepository
) : UseCase<User, LoginData>() {

    override suspend fun run(params: LoginData): User {
        return authenticationProviderFactory.create(params.source).login(params).also { user ->
            userSessionRepository.create(mapToUserSession(user))
        }
    }

    private fun mapToUserSession(user: User): UserSession {
        return UserSession(id = user.id)
    }

}

data class LoginData(
    var source: AuthenticationProviderSource,
    var password: String = "",
    var email: String = ""
)

