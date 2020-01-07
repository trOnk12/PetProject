package com.example.myapplication.data.repository

import com.example.myapplication.data.source.comment.remote.CommentRemoteSource
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val remoteSource: CommentRemoteSource
) : CommentRepository {

    override fun get(id: String): Comment = remoteSource.comment(id)

    override fun get(): List<Comment> = remoteSource.comments()

}
