package com.example.myapplication.data.network

import javax.inject.Inject

class CommentService
@Inject constructor(
    private val commentApi: CommentApi
) : CommentApi {
    override fun get(id: String) = commentApi.get(id)
    override fun get() = commentApi.get()
}
