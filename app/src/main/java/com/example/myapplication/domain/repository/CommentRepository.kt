package com.example.myapplication.domain.repository

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.core.network.data.Outcome
import com.example.myapplication.domain.model.Comment

interface CommentRepository {
    fun comments(): Either<Failure, List<Comment>>
}