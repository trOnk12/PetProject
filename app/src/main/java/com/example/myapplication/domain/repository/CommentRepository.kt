package com.example.myapplication.domain.repository

import com.example.myapplication.domain.entity.Comment

interface CommentRepository {
    fun get(): List<Comment>
    fun get(id: String): Comment
}
