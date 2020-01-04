package com.example.myapplication.domain.usecase

import com.example.core.interactor.None
import com.example.core.interactor.UseCase
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class GetCommentsUseCase
@Inject constructor(
    private var commentRepository: CommentRepository,
    private var userRepository: UserRepository
) : UseCase<List<Comment>, None>() {

    override suspend fun run(params: None): List<Comment> {
        val comments = commentRepository.comments()
        val user = userRepository.getUser()

        user?.let {
            comments.forEach {comment ->
                if (it.favouriteCommentsId.contains(comment.id)) {
                    comment.isFavourite = true
                }
            }
        }

        return comments
    }

}