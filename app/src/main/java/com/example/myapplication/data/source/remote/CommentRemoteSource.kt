package com.example.myapplication.data.source.remote

import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.core.functional.Transformer
import com.example.myapplication.data.entity.mapToDomain
import com.example.myapplication.data.network.CommentService
import com.example.myapplication.domain.model.Comment
import javax.inject.Inject

class CommentRemoteSource
@Inject constructor(
    private var commentService: CommentService
) {

    fun comments(): Either<Failure, List<Comment>> {
        return Transformer()(commentService.comments(), { it.mapToDomain() }, emptyList())
    }

}