package com.example.myapplication.domain.repository

import com.example.core.functional.Result
import com.example.myapplication.domain.model.Comment

interface CommentRepository {
    fun comments(): List<Comment>
    fun comment(id: String):Comment
}