package com.example.myapplication.domain.usecase

import com.example.core.interactor.UseCase
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.repository.CommentRepository

import javax.inject.Inject

class GetCommentUseCase
@Inject constructor(
    private val commentRepository: CommentRepository
) : UseCase<Comment, String>() {

    override suspend fun run(params: String): Comment {
        return commentRepository.get(params)
    }

}
