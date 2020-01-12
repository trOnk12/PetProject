package com.example.myapplication.di.modules.authentication

import com.example.myapplication.data.authentication.FireBaseAuthProvider
import com.example.myapplication.domain.authentication.AuthenticationProvider
import com.example.myapplication.domain.authentication.AuthenticationProviderFactory
import com.example.myapplication.domain.authentication.AuthenticationSource
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthenticationModule {

    @Binds
    internal abstract fun bindAuthenticationFactory(factory: AuthenticationFactory): AuthenticationProviderFactory

    @Binds
    @IntoMap
    @AuthenticationKey(AuthenticationSource.FireBase)
    internal abstract fun firebaseAuthProvider(source: FireBaseAuthProvider): AuthenticationProvider
}
