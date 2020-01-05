package com.example.myapplication.feature.commentlist.ui.list

import com.example.myapplication.domain.entity.Comment

sealed class CommentsListViewEvent {
    data class OpenCommentDetail(val comment: Comment) : CommentsListViewEvent()
    object OpenImageSourceDialog : CommentsListViewEvent()
}
