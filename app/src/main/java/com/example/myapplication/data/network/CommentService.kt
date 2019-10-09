package com.example.myapplication.data.network

import com.example.myapplication.data.model.DataComment
import retrofit2.Retrofit

class CommentService constructor(retrofit: Retrofit) : CommentApi {
    private val commentApi by lazy {
        retrofit.create(CommentApi::class.java)
    }

    override suspend fun getComments(): List<DataComment> = commentApi.comments()
}