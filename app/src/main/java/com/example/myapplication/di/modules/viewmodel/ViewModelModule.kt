package com.example.myapplication.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.comment.CommentViewModel
import com.example.myapplication.ui.login.LoginViewModel
import com.example.myapplication.ui.register.RegisterViewModel
import com.example.myapplication.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel:SplashViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    internal abstract fun registerViewModel(viewModel:RegisterViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommentViewModel::class)
    internal abstract fun commentViewModel(viewModel: CommentViewModel): ViewModel

}