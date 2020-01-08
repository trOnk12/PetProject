package com.example.myapplication.di.modules.authentication

import com.example.myapplication.data.authentication.AuthenticationFactory
import com.example.myapplication.data.authentication.FirebaseAuthProvider
import com.example.myapplication.domain.authentication.AuthenticationProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthenticationModule {

    @Binds
    internal abstract fun bindAuthenticationFactory(factory: AuthenticationFactory): AuthenticationFactory

    @Binds
    @IntoMap
    @AuthenticationKey(FirebaseAuthProvider::class)
    internal abstract fun splashViewModel(viewModel: FirebaseAuthProvider): AuthenticationProvider

}