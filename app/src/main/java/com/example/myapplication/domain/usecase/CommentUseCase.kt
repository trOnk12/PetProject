package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.CommentRepository
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.Outcome

class CommentUseCase(var commentRepository: CommentRepository) {

    suspend fun getComment(): Outcome<List<Comment>> {
        return commentRepository.getComments()
    }

}