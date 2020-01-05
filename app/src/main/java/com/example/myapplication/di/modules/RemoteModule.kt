package com.example.myapplication.di.modules

import com.example.myapplication.data.firebase.Authenticator
import com.example.myapplication.data.firebase.FireBaseStorage
import com.example.myapplication.data.firebase.UserFireStore
import com.example.myapplication.data.source.UserRemoteSource
import com.example.myapplication.data.source.remote.UserRemoteSourceImpl
import dagger.Module
import dagger.Provides

@Module
class RemoteModule {

    @Provides
    fun provideUserRemoteSource(
        authenticator: Authenticator,
        userFireStore: UserFireStore,
        fireBaseStorage: FireBaseStorage
    ): UserRemoteSource {
        return UserRemoteSourceImpl(authenticator, userFireStore, fireBaseStorage)
    }
}
