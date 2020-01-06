package com.example.myapplication.data.source.remote

import com.example.myapplication.data.model.CommentDto
import com.example.myapplication.data.model.mapToDomain
import com.example.myapplication.data.network.CommentService
import com.example.myapplication.domain.entity.Comment
import javax.inject.Inject
import retrofit2.Call

class CommentRemoteSource
@Inject constructor(
    private var commentService: CommentService
) {

    fun comments(): List<Comment> {
        return transform(
            commentService.get(),
            { it.mapToDomain() },
            emptyList()
        )
    }

    fun comment(id: String): Comment {
        return transform(
            commentService.get(id),
            { it.mapToDomain() },
            CommentDto(2, 1, "TEST", "TEST")
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
