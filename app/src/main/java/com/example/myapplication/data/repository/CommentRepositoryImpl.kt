package com.example.myapplication.data.repository

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.myapplication.data.local.sharedpreferences.FavouriteStatus
import com.example.myapplication.data.source.local.CommentLocalSource
import com.example.myapplication.data.source.remote.CommentRemoteSource
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.repository.CommentRepository

class CommentRepositoryImpl(
    private val remoteSource: CommentRemoteSource,
    private val localSource: CommentLocalSource
) : CommentRepository {

    override fun addToFavourite(id: String): Either<Failure,FavouriteStatus> = localSource.addToFavourite(id)

    override fun comment(id: String): Either<Failure, Comment> = remoteSource.comment(id)

    override fun comments(): Either<Failure, List<Comment>> = remoteSource.comments()
}