package com.example.myapplication.domain.usecase

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.core.interactor.UseCase
import com.example.myapplication.data.local.sharedpreferences.FavouriteStatus
import com.example.myapplication.domain.repository.CommentRepository
import javax.inject.Inject

class AddCommentToFavourite
@Inject constructor(
    private val commentRepository: CommentRepository
) :
    UseCase<FavouriteStatus, String>() {
    override suspend fun run(params: String): Either<Failure, FavouriteStatus> {
        return commentRepository.addToFavourite(params)
    }
}