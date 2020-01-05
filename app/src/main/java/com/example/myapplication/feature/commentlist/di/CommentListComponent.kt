package com.example.myapplication.feature.commentlist.di

import com.example.myapplication.di.components.CoreComponent
import com.example.myapplication.di.scopes.FeatureScope
import com.example.myapplication.feature.commentlist.ui.list.CommentsListFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [CommentListModule::class],
    dependencies = [CoreComponent::class]
)
interface CommentListComponent {
    fun inject(fragment: CommentsListFragment)
}