package com.example.myapplication.ui.model

import com.example.myapplication.domain.entity.Comment

sealed class NavigationState {
    data class CommentDetail(val comment: Comment) : NavigationState()
    object OptionDialog : NavigationState()
}
