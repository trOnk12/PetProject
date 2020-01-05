package com.example.myapplication.data.repository

import com.example.myapplication.data.source.local.CommentLocalSource
import com.example.myapplication.data.source.remote.CommentRemoteSource
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.repository.CommentRepository

class CommentRepositoryImpl(
    private val remoteSource: CommentRemoteSource,
    private val localSource: CommentLocalSource
) : CommentRepository {

    override fun comment(id: String): Comment = remoteSource.comment(id)
    override fun comments(): List<Comment> = remoteSource.comments()
}
