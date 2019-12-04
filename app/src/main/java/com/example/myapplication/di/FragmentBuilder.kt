package com.example.myapplication.di

import com.example.myapplication.presentation.comments.CommentsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindCommentsFragment() : CommentsFragment
}