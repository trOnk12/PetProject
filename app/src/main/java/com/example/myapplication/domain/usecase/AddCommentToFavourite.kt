package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.CommentRepository
import javax.inject.Inject

class AddCommentToFavourite
@Inject constructor(
    private val commentRepository: CommentRepository
)
{
//    UseCase<FavouriteStatus, String>() {
//    override suspend fun run(params: String): Result<FavouriteStatus> {
//        return commentRepository.addToFavourite(params)
//    }
}