package com.example.myapplication.feature.commentlist.di

import com.example.myapplication.di.components.CoreComponent
import com.example.myapplication.di.scopes.FeatureScope
import com.example.myapplication.feature.commentlist.ui.list.CommentsListFilterFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [CommentsListFilterModule::class],
    dependencies = [CoreComponent::class]
)
interface CommentsListFilterComponent {
    fun inject(fragment: CommentsListFilterFragment)
}