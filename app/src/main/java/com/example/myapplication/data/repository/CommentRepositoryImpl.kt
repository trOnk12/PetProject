package com.example.myapplication.data.repository

import com.example.myapplication.data.source.local.CommentLocalSource
import com.example.myapplication.data.source.remote.CommentRemoteSource
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val remoteSource: CommentRemoteSource,
    private val localSource: CommentLocalSource
) : CommentRepository {

    override fun get(id: String): Comment = remoteSource.comment(id)
    override fun get(): List<Comment> = remoteSource.comments()
}
