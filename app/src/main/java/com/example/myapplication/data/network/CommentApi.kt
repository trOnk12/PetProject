package com.example.myapplication.data.network

import com.example.myapplication.data.entity.CommentEntity
import retrofit2.Call
import retrofit2.http.GET

interface CommentApi {
    companion object {
        private const val COMMENTS = "/comments"
        private const val COMMENT = "/comments"
    }

    @GET(COMMENTS)
    fun comments(): Call<List<CommentEntity>>

    @GET(COMMENT)
    fun comment(id: String): Call<CommentEntity>
}
