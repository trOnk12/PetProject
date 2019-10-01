package com.example.myapplication.data.source.remote

import com.example.core.data.Outcome
import com.example.myapplication.data.model.mapToDomain
import com.example.myapplication.data.network.ApiService
import com.example.myapplication.domain.entity.Comment
import retrofit2.HttpException

class CommentRemoteSource(var apiService: ApiService) {

    suspend fun getComments(): Outcome<List<Comment>> {
        return try {
            Outcome.Success((apiService.getComments().mapToDomain()))
        } catch (ex: HttpException) {
            Outcome.Failure(ex.message(), ex)
        }
    }

}