package com.example.myapplication.domain.repository

import com.example.myapplication.domain.entity.Comment

interface CommentRepository {
    fun comments(): List<Comment>
    fun comment(id: String):Comment
}