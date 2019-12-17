package com.example.myapplication.domain.usecase

import com.example.core.functional.Result
import com.example.core.interactor.None
import com.example.core.interactor.UseCase
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.repository.CommentRepository

class GetComments(private var commentRepository: CommentRepository) : UseCase<List<Comment>, None>() {

    override suspend fun run(params: None):List<Comment> {
        return commentRepository.comments()
    }

}