package com.example.myapplication.domain.usecase

import com.example.core.interactor.None
import com.example.core.interactor.UseCase
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject
import com.example.core.functional.Result
import com.example.myapplication.domain.entity.User
import java.lang.IllegalStateException

class AddCommentToFavouriteUseCase
@Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val userRepository: UserRepository
) : UseCase<Comment, Comment>() {

    override suspend fun run(params: Comment): Comment {
        return when (val result = getUserUseCase(None())) {
            is Result.Success -> updateComment(result.data, params)
            is Result.Error -> throw result.exception
            else -> throw IllegalStateException()
        }
    }

    private suspend fun updateComment(
        user: User,
        comment: Comment
    ): Comment {
        return comment.toggleIsFavourite().apply {
            updateUser(user, comment)
        }
    }

    private suspend fun updateUser(user: User, comment: Comment) {
        if (user.favouriteCommentsId.contains(comment.id)) {
            user.favouriteCommentsId.remove(comment.id)
        } else {
            user.favouriteCommentsId.add(comment.id)
        }

        userRepository.update(user)
    }

}
