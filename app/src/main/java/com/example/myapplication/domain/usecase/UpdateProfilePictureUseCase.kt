package com.example.myapplication.domain.usecase

import com.example.core.interactor.UseCase
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository

class UpdateProfilePictureUseCase(private val userRepository: UserRepository) : UseCase<User, String>() {
    override suspend fun run(params: String): User {
        return userRepository.uploadProfilePicture(params)
    }
}