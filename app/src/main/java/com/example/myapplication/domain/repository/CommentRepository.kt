package com.example.myapplication.domain.repository

import com.example.myapplication.domain.entity.Comment
import com.example.core.Outcome

interface CommentRepository {

    suspend fun getComments(): com.example.core.Outcome<List<Comment>>

}