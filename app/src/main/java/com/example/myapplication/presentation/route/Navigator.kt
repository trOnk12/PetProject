package com.example.myapplication.presentation.route

import android.content.Context
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.presentation.MainActivity
import com.example.myapplication.presentation.comments.CommentsDetailActivity

object Navigator {

    fun showMain(context: Context) = context.startActivity(MainActivity.callingIntent(context))

    fun showCommentDetails(context: Context, comment: Comment) =
        context.startActivity(CommentsDetailActivity.callingIntent(context, comment))

}