package com.example.myapplication.data.source.remote


import com.example.myapplication.data.entity.CommentEntity
import com.example.myapplication.data.entity.mapToDomain
import com.example.myapplication.data.network.CommentService
import com.example.myapplication.domain.model.Comment
import retrofit2.Call
import javax.inject.Inject
import com.example.core.functional.Result

class CommentRemoteSource
@Inject constructor(
    private var commentService: CommentService
) {

    fun comments(): List<Comment> {
        return transform(
            commentService.comments(),
            { it.mapToDomain() },
            emptyList()
        )
    }

    fun comment(id: String): Comment {
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