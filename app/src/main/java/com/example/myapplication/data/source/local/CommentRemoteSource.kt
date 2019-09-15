package com.example.myapplication.data.source.local

import com.example.myapplication.data.mapper.CommentDataMapper
import com.example.myapplication.data.model.DataComment
import com.example.myapplication.data.network.ApiService
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.Outcome
import retrofit2.HttpException

class CommentRemoteSource(var commentDataMapper: CommentDataMapper,var apiService: ApiService) {

    suspend fun getComments(): Outcome<List<Comment>> {
        return try {
            Outcome.Success((apiService.getComments().map {
                commentDataMapper.map(it)
            }))
        } catch (ex: HttpException) {
            Outcome.Failure(ex.message(), ex)
        }
    }

}