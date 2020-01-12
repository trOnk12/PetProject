package com.example.myapplication.data.repository

import com.example.myapplication.data.source.comment.remote.CommentRemoteSource
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.repository.ICommentRepository
import javax.inject.Inject

class CommentRepository
@Inject constructor(
    private val remoteSource: CommentRemoteSource
) : ICommentRepository {

    override fun get(id: String): Comment = remoteSource.get(id)

    override fun get(): List<Comment> = remoteSource.get()
}
