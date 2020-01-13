package com.example.myapplication.feature.commentlist.di

import com.example.myapplication.di.scopes.FeatureScope
import com.example.myapplication.feature.commentlist.ui.list.CommentsListFilterFragment
import com.example.myapplication.feature.commentlist.ui.list.CommentsListFragment
import dagger.Module
import dagger.Provides


@Module
class CommentsListFilterModule(private val fragment: CommentsListFilterFragment) {

    @FeatureScope
    @Provides
    fun provideCommentListFragment(): CommentsListFilterFragment {
        return fragment
    }

}