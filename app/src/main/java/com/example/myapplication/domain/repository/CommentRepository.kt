package com.example.myapplication.domain.repository

import androidx.recyclerview.widget.DiffUtil
import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.myapplication.domain.model.Comment

interface CommentRepository {
    fun comments(): Either<Failure, List<Comment>>
    fun comment(id: String): Either<Failure, Comment>
}