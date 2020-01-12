package com.example.myapplication.domain.usecase

import com.example.core.functional.Result
import com.example.core.interactor.None
import com.example.core.interactor.UseCase
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.repository.IUserRepository
import java.lang.IllegalStateException
import javax.inject.Inject

class AddCommentToFavouriteUseCase
@Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val userRepository: IUserRepository
) : UseCase<Comment, Comment>() {

    override suspend fun run(params: Comment): Comment {
        return when (val result = getUserUseCase(None())) {
            is Result.Success -> updateUser(result.data, params)
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

    private suspend fun updateUser(user: User, comment: Comment): Comment {
        if (user.favouriteCommentsId.contains(comment.id)) {
            user.favouriteCommentsId.remove(comment.id)
        } else {
            user.favouriteCommentsId.add(comment.id)
        }

        userRepository.update(user)
        return comment
    }
}
