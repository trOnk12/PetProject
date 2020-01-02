package com.example.myapplication.di

import com.example.myapplication.ui.comment.CommentFragment
import com.example.myapplication.ui.dialog.ProfileImageChooserFragment
import com.example.myapplication.ui.login.LoginFragment
import com.example.myapplication.ui.register.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindLoginFragment() : LoginFragment

    @ContributesAndroidInjector
    abstract fun bindRegisterFragment() : RegisterFragment

    @ContributesAndroidInjector
    abstract fun bindCommentsFragment() : CommentFragment

    @ContributesAndroidInjector
    abstract fun bindProfileImageChooserFragment() : ProfileImageChooserFragment
}
