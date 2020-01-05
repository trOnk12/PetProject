package com.example.myapplication.di

import com.example.myapplication.feature.commentlist.di.CommentListModule
import com.example.myapplication.feature.commentlist.ui.list.CommentsListFragment
import com.example.myapplication.feature.commentlist.ui.list.dialog.ImageSourceDialogFragment
import com.example.myapplication.feature.login.ui.LoginFragment
import com.example.myapplication.feature.register.ui.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun bindRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector(modules = [CommentListModule::class])
    abstract fun bindCommentsFragment(): CommentsListFragment

    @ContributesAndroidInjector
    abstract fun bindProfileImageChooserFragment(): ImageSourceDialogFragment
}
