package com.example.myapplication.domain.usecase

import com.example.core.interactor.None
import com.example.core.interactor.UseCase
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.data.repository.UserSessionRepository
import com.example.myapplication.domain.entity.User
import javax.inject.Inject

class GetUserUseCase
@Inject constructor(
    private val userRepository: UserRepository,
    private val userSessionRepository: UserSessionRepository
) : UseCase<User, None>() {

    override suspend fun run(params: None): User {
        return userSessionRepository.get().run {
            userRepository.get(id)
        }
    }
}
