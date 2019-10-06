package com.example.myapplication.domain.usecase

import com.example.core.network.Outcome
import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.entity.Comment

class CommentUseCase(private var commentRepository: CommentRepository) {

    suspend fun getComment(): Outcome<List<Comment>> = commentRepository.getComments()

}