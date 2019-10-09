package com.example.myapplication.data.source.remote

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.myapplication.data.network.CommentsAPI
import com.example.myapplication.domain.entity.Comment

class CommentRemoteSource(var commentsAPI: CommentsAPI) {

    fun comments(): Either<Failure, List<Comment>>{

    }

//    suspend fun getComments(): Outcome<List<Comment>> {
//        return try {
//            Outcome.Success((commentsAPI.getComments().mapToDomain()))
//        } catch (ex: HttpException) {
//            Outcome.Failure(ex.message(), ex)
//        }
//    }

}