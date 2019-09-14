package com.example.myapplication.domain

import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.Outcome

interface CommentRepository {
    suspend fun getComments(): Outcome<List<Comment>>
}