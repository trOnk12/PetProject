package com.example.myapplication.domain.usecase

import com.example.core.functional.Result
import com.example.core.interactor.UseCase
import com.example.core.interactor.UseCase.None
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.repository.CommentRepository

class GetComments(private var commentRepository: CommentRepository) : UseCase<List<Comment>, None>() {

    override suspend fun run(params: None): Result<List<Comment>> {
        return commentRepository.comments()
    }

}