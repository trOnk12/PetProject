package com.example.myapplication.data.network.service

import com.example.myapplication.data.network.responses.CommentResponse
import retrofit2.Call
import retrofit2.http.GET

interface CommentService {
    companion object {
        private const val COMMENTS = "/posts"
        private const val COMMENT = "/get"
    }

    @GET(COMMENTS)
    fun get(): Call<List<CommentResponse>>

    @GET(COMMENT)
    fun get(id: String): Call<CommentResponse>
}
