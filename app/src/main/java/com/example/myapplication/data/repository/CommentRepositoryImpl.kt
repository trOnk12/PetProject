package com.example.myapplication.data.repository

import com.example.myapplication.data.source.remote.CommentRemoteSource
import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.entity.Comment
import com.example.core.data.Outcome

class CommentRepositoryImpl(
    var remoteSource: CommentRemoteSource
) : CommentRepository {

    override suspend fun getComments(): Outcome<List<Comment>> = remoteSource.getComments()

}