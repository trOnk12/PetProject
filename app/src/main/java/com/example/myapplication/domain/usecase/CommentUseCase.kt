package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.entity.Comment
import com.example.core.Outcome

class CommentUseCase(private var commentRepository: CommentRepository) {

    suspend fun getComment(): com.example.core.Outcome<List<Comment>> = commentRepository.getComments()

}