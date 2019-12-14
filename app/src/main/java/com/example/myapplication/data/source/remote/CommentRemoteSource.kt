package com.example.myapplication.data.source.remote

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.myapplication.data.entity.CommentEntity
import com.example.myapplication.data.entity.mapToDomain
import com.example.myapplication.data.network.CommentService
import com.example.myapplication.domain.model.Comment
import retrofit2.Call
import javax.inject.Inject

class CommentRemoteSource
@Inject constructor(
    private var commentService: CommentService
) {

    fun comments(): Either<Failure, List<Comment>> {
        return transform(
            commentService.comments(),
            { it.mapToDomain() },
            emptyList()
        )
    }

    fun comment(id: String): Either<Failure, Comment> {
        return transform(
            commentService.comment(id),
            { it.mapToDomain() },
            CommentEntity(2, 1, "TEST", "TEST")
        )
    }

    private fun <T, R> transform(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform(response.body() ?: default))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }

}