package com.example.myapplication.data.source.remote

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.core.functional.Transformer
import com.example.myapplication.data.network.CommentApi
import com.example.myapplication.data.network.CommentService
import com.example.myapplication.domain.entity.Comment

class CommentRemoteSource(var transformer: Transformer,var commentService: CommentService) {

    fun comments(): Either<Failure, List<Comment>> = transformer(commentService.getComments(),)

//    suspend fun getComments(): Outcome<List<Comment>> {
//        return try {
//            Outcome.Success((commentApi.getComments().mapToDomain()))
//        } catch (ex: HttpException) {
//            Outcome.Failure(ex.message(), ex)
//        }
//    }

}