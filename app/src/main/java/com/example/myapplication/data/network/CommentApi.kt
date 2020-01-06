package com.example.myapplication.data.network

import com.example.myapplication.data.model.CommentDto
import retrofit2.Call
import retrofit2.http.GET

interface CommentApi {
    companion object {
        private const val COMMENTS = "/get"
        private const val COMMENT = "/get"
    }

    @GET(COMMENTS)
    fun get(): Call<List<CommentDto>>

    @GET(COMMENT)
    fun get(id: String): Call<CommentDto>
}
