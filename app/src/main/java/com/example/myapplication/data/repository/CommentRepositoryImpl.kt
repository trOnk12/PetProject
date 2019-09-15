package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.CommentDataMapper
import com.example.myapplication.data.source.local.CommentRemoteSource
import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.Outcome
import retrofit2.HttpException

class CommentRepositoryImpl(
    var remoteSource: CommentRemoteSource
) : CommentRepository {

    override suspend fun getComments(): Outcome<List<Comment>> = remoteSource.getComments()

}