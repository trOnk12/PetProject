package com.example.myapplication.feature.commentlist.di

import com.example.myapplication.feature.commentlist.ui.list.CommentListListeners
import com.example.myapplication.feature.commentlist.ui.list.CommentsListFragment
import com.example.myapplication.feature.commentlist.ui.list.CommentsListViewModel
import com.example.myapplication.feature.commentlist.ui.list.adapter.CommentAdapter
import dagger.Module
import dagger.Provides


@Module
class CommentListModule {

    @Provides
    fun provideCommentListAdapter(
        commentsListViewModel: CommentsListViewModel,
        commentsListFragment: CommentsListFragment
    ): CommentAdapter {
        return CommentAdapter(commentsListViewModel as CommentListListeners.CommentEventListener, commentsListFragment)
    }

}
