package com.example.myapplication.data.repository


import com.example.myapplication.data.source.local.CommentLocalSource
import com.example.myapplication.data.source.remote.CommentRemoteSource
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.repository.CommentRepository
import com.example.core.functional.Result

class CommentRepositoryImpl(
    private val remoteSource: CommentRemoteSource,
    private val localSource: CommentLocalSource
) : CommentRepository {

    override fun comment(id: String): Result<Comment> = remoteSource.comment(id)

    override fun comments(): Result<List<Comment>> = remoteSource.comments()
}