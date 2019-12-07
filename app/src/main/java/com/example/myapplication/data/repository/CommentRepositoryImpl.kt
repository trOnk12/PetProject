package com.example.myapplication.data.repository

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.myapplication.data.source.remote.CommentRemoteSource
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.repository.CommentRepository

class CommentRepositoryImpl(
    var remoteSource: CommentRemoteSource
) : CommentRepository {
    override fun comment(id: String): Either<Failure, Comment> = remoteSource.comment(id)
    override fun comments(): Either<Failure, List<Comment>> = remoteSource.comments()
}