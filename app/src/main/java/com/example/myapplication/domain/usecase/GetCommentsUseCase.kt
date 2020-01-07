package com.example.myapplication.domain.usecase

import com.example.core.interactor.None
import com.example.core.interactor.UseCase
import com.example.myapplication.domain.entity.Comment
import com.example.core.functional.Result
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.repository.CommentRepository
import java.lang.IllegalStateException
import javax.inject.Inject

class GetCommentsUseCase
@Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private var commentRepository: CommentRepository
) : UseCase<List<Comment>, None>() {

    override suspend fun run(params: None): List<Comment> {
        val comments = commentRepository.get()

        return getUserUseCase(None()).let { result ->
            when (result) {
                is Result.Success -> combineWithComments(result.data, comments)
                is Result.Error -> throw result.exception
                else -> throw IllegalStateException()
            }
        }
    }

    private fun combineWithComments(user: User, comments: List<Comment>): List<Comment> {
        return comments.map { comment ->
            if (user.favouriteCommentsId.contains(comment.id)) {
                comment.copy(isFavourite = true)
            } else {
                comment.copy(isFavourite = false)
            }
        }
    }

}
