package com.example.myapplication.di.modules.datasource

import com.example.myapplication.data.source.RemoteSource
import com.example.myapplication.data.source.user.remote.UserRemoteSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideUserRemoteSource(
        userRemoteSource: UserRemoteSource
    ): RemoteSource {
        return userRemoteSource
    }

}
