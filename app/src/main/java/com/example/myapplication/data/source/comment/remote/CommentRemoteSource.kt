package com.example.myapplication.data.source.comment.remote

import com.example.myapplication.data.network.responses.mapToDomain
import com.example.myapplication.data.network.service.CommentService
import com.example.myapplication.data.source.ICommentRemoteSource
import com.example.myapplication.domain.entity.Comment
import javax.inject.Inject
import retrofit2.Call

class CommentRemoteSource
@Inject constructor(
    private var commentService: CommentService
) : ICommentRemoteSource {

    override fun get(): List<Comment> {
        return transform(
            commentService.get(),
            { it.mapToDomain() },
            emptyList()
        )
    }

    override fun get(id: String): Comment {
        return transform(
            commentService.get(id),
            { it.mapToDomain() },
            Comment.EMPTY
        )
    }

    private fun <T, R> transform(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): R {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> transform(response.body() ?: default)
                false -> throw Exception("Something went wrong.")
            }
        } catch (exception: Exception) {
            throw exception
        }
    }
}
