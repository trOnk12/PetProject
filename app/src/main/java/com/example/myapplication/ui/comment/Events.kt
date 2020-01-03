package com.example.myapplication.ui.comment

import com.example.myapplication.domain.model.Comment

interface Events{
    interface CommentEventListener {
        fun onStarClicked(comment: Comment)
        fun onCommentClicked(comment: Comment)
    }

    interface ToolBarEventListener {
        fun onProfilePictureClicked()
    }
}