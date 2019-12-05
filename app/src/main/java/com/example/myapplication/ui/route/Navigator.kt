package com.example.myapplication.ui.route

import android.content.Context
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.ui.comments.CommentsDetailActivity

object Navigator {

    fun showMain(context: Context) = context.startActivity(MainActivity.callingIntent(context))

    fun showCommentDetails(context: Context, comment: Comment) =
        context.startActivity(CommentsDetailActivity.callingIntent(context, comment))

}