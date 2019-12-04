package com.example.myapplication.data.network

class CommentService
constructor(
    private val commentApi: CommentApi
) : CommentApi {

    override fun comments() = commentApi.comments()

}