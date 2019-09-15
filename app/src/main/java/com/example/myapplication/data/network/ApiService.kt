package com.example.myapplication.data.network

import com.example.myapplication.data.model.DataComment
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/comments")
    suspend fun getComments(): List<DataComment>

}