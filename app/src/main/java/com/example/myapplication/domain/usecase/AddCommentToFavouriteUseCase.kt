package com.example.myapplication.domain.usecase

import com.example.core.interactor.UseCase
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class AddCommentToFavouriteUseCase
@Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Comment, Comment>() {

    override suspend fun run(params: Comment): Comment {
        return userRepository.addCommentToFavourite(params)
    }

}