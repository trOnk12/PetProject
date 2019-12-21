package com.example.myapplication.di

import com.example.myapplication.ui.comment.CommentFragment
import com.example.myapplication.ui.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindCommentsFragment() : CommentFragment

    @ContributesAndroidInjector
    abstract fun bindLoginFragment() : LoginFragment
}