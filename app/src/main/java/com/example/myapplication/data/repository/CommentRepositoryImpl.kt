package com.example.myapplication.data.repository

import com.example.core.network.data.Outcome
import com.example.myapplication.data.source.remote.CommentRemoteSource
import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.entity.Comment


class CommentRepositoryImpl(
    var remoteSource: CommentRemoteSource
) : CommentRepository {

    override suspend fun getComments(): Outcome<List<Comment>> = remoteSource.getComments()

}