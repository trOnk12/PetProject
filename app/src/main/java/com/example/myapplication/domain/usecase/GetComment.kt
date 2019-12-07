package com.example.myapplication.domain.usecase

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.core.interactor.UseCase
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.repository.CommentRepository

class GetComment(private val commentRepository: CommentRepository) : UseCase<Comment, String>() {
    override suspend fun run(params: String): Either<Failure, Comment> {
        return commentRepository.comment(params)
    }
}