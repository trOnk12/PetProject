package com.example.myapplication.presentation.route

import android.content.Context
import android.util.Log
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.presentation.main.MainActivity
import com.example.myapplication.presentation.comments.CommentsActivity
import com.example.myapplication.presentation.comments.CommentsDetailActivity
import com.example.myapplication.presentation.search.SearchActivity

class Navigator {

    fun showMain(context: Context) = context.startActivity(MainActivity.callingIntent(context))

    fun showCommentDetails(context: Context, comment: Comment) =
        context.startActivity(CommentsDetailActivity.callingIntent(context, comment))

}