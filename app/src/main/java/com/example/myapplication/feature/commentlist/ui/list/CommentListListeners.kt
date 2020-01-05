package com.example.myapplication.feature.commentlist.ui.list

import com.example.myapplication.domain.entity.Comment

interface CommentListListeners {
    interface CommentEventListener {
        fun onStarClicked(comment: Comment)
        fun onCommentClicked(comment: Comment)
    }

    interface ToolBarEventListener {
        fun onProfilePictureClicked()
    }
}
