package com.example.myapplication.data.network

import javax.inject.Inject

class CommentService
@Inject constructor(
    private val commentApi: CommentApi
) : CommentApi {
    override fun comment(id: String) = commentApi.comment(id)
    override fun comments() = commentApi.comments()
}