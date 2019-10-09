package com.example.myapplication.ui.route

import android.content.Context
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.ui.comments.CommentsActivity

class Navigator {

    private fun showComments(context: Context) = context.startActivity(CommentsActivity.callingIntent(context))

    fun showMain(context: Context) {
        showComments(context)
    }

    fun showCommentDetails(comment: Comment) {

    }

}