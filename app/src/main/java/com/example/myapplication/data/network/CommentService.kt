package com.example.myapplication.data.network

import retrofit2.Retrofit

class CommentService constructor(retrofit: Retrofit):CommentApi{
    private val commentApi by lazy {
        retrofit.create(CommentApi::class.java)
    }

    override fun comments() = commentApi.comments()
}