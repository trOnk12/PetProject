package com.example.myapplication.feature.commentlist.di

import com.example.myapplication.feature.commentlist.ui.list.CommentListListeners
import com.example.myapplication.feature.commentlist.ui.list.CommentsListFragment
import com.example.myapplication.feature.commentlist.ui.list.CommentsListViewModel
import com.example.myapplication.feature.commentlist.ui.list.adapter.CommentAdapter
import dagger.Module
import dagger.Provides

@Module
class CommentListModule(
    private val fragment: CommentsListFragment
) {

    @Provides
    fun provideCommentListFragment(): CommentsListFragment {
        return fragment
    }

    @Provides
    fun provideCommentListAdapter(
        viewModel: CommentsListViewModel,
        fragment: CommentsListFragment
    ): CommentAdapter {
        return CommentAdapter(viewModel as CommentListListeners.CommentEventListener, fragment)
    }
}
