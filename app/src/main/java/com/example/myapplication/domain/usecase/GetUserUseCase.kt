package com.example.myapplication.domain.usecase

import com.example.core.interactor.None
import com.example.core.interactor.UseCase
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase
@Inject constructor(
    private val userRepository: UserRepository
) : UseCase<User, None>() {
    override suspend fun run(params: None): User {
        return userRepository.getUser()
    }
}