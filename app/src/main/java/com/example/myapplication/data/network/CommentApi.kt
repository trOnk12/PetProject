package com.example.myapplication.data.network

import com.example.myapplication.data.model.DataComment
import retrofit2.Call
import retrofit2.http.GET

interface CommentApi {
    companion object {
        private const val COMMENTS = "/comments"
    }

    @GET(COMMENTS)
     fun getComments(): Call<List<DataComment>>

}