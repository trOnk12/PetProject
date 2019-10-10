package com.example.myapplication.data.repository

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.myapplication.data.source.remote.CommentRemoteSource
import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.model.Comment

class CommentRepositoryImpl(
    var remoteSource: CommentRemoteSource
) : CommentRepository {
    override fun comments(oldValue: List<Comment>): Either<Failure, List<Comment>> = remoteSource.comments(oldValue)

    override fun comments(): Either<Failure, List<Comment>> = remoteSource.comments()
}