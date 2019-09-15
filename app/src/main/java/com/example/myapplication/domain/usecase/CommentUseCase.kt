package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.Outcome

class CommentUseCase(private var commentRepository: CommentRepository) {

    suspend fun getComment(): Outcome<List<Comment>> = commentRepository.getComments()

}