package com.example.myapplication.domain.usecase

import com.example.core.interactor.None
import com.example.core.interactor.UseCase
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.repository.ProfilePictureRepository
import com.example.myapplication.domain.repository.UserRepository
import com.example.core.functional.Result
import java.lang.IllegalStateException
import javax.inject.Inject

class UpdateProfilePictureUseCase
@Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val profilePictureRepository: ProfilePictureRepository,
    private val userRepository: UserRepository
) : UseCase<User, String>() {

    override suspend fun run(params: String): User {
        return when (val result = getUserUseCase(None())) {
            is Result.Success -> updateProfilePicture(uri = params, user = result.data)
            is Result.Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }

    private suspend fun updateProfilePicture(uri: String, user: User): User {
        return profilePictureRepository.upload(uri).run {
            userRepository.update(user.copy(profileImageUrl = uri))
        }
    }

}
