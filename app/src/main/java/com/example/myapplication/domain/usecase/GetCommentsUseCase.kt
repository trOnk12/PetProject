package com.example.myapplication.domain.usecase

import com.example.core.interactor.UseCase
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.repository.CommentRepository
import javax.inject.Inject

class GetCommentsUseCase
@Inject constructor(
    private var commentRepository: CommentRepository
) : UseCase<List<Comment>, User?>() {

    override suspend fun run(params: User?): List<Comment> {
        val comments = commentRepository.comments()

        return if (params == null) comments else mapCommentToFavourite(comments, params)
    }

    private fun mapCommentToFavourite(
        comments: List<Comment>,
        params: User
    ): List<Comment> {
        return comments.map { comment ->
            val isCommentFavourite = params.favouriteCommentsId.contains(comment.id)

            if (isCommentFavourite)
                Comment(comment.userId, comment.id, comment.title, comment.body, true)
            else
                comment
        }
    }
}
