package com.example.myapplication.data.network

import com.example.myapplication.data.entity.CommentEntity
import retrofit2.Call
import retrofit2.http.GET

interface CommentApi {
    companion object {
        private const val COMMENTS = "/comments"
    }

    @GET(COMMENTS)
     fun comments(): Call<List<CommentEntity>>

}