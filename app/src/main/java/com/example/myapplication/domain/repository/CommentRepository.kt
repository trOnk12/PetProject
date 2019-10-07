package com.example.myapplication.domain.repository


import com.example.core.network.data.Outcome
import com.example.myapplication.domain.entity.Comment

interface CommentRepository {

    suspend fun getComments(): Outcome<List<Comment>>

}