package com.example.myapplication.feature.commentlist.ui.list

import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.User
import java.lang.Exception

sealed class CommentListViewState {
    data class RenderComments(val comments: List<Comment>) : CommentListViewState()
    data class ShowSnackBarMessage(val message: String) : CommentListViewState()
    data class RenderUser(val user: User) : CommentListViewState()
    data class Failure(val exception: Exception) : CommentListViewState()
}
