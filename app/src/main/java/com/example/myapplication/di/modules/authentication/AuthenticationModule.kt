package com.example.myapplication.di.modules.authentication

import com.example.myapplication.data.authentication.FireBaseAuth
import com.example.myapplication.domain.authentication.Authentication
import com.example.myapplication.domain.authentication.AuthenticationProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthenticationModule {

    @Binds
    internal abstract fun bindAuthenticationFactory(factory: AuthenticationFactory): AuthenticationProviderFactory

    @Binds
    @IntoMap
    @AuthenticationKey(AuthenticationSources.FIREBASE)
    internal abstract fun firebaseAuthProvider(source: FireBaseAuth): Authentication
}
