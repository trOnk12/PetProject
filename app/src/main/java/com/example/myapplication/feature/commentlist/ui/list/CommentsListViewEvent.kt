package com.example.myapplication.feature.commentlist.ui.list

import com.example.myapplication.domain.entity.Comment

sealed class CommentsListViewEvent {
    object OpenImageSourceDialog : CommentsListViewEvent()
    data class ShowSnackBarMessage(val message: String) : CommentsListViewEvent()
    data class OpenCommentDetail(val comment: Comment) : CommentsListViewEvent()
}
