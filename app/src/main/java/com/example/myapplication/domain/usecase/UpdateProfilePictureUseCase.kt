package com.example.myapplication.domain.usecase

import com.example.core.interactor.UseCase
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class UpdateProfilePictureUseCase
@Inject constructor(
    private val userRepository: UserRepository
) : UseCase<User, String>() {
    override suspend fun run(params: String): User {
        return userRepository.uploadProfilePicture(params)
    }
}
