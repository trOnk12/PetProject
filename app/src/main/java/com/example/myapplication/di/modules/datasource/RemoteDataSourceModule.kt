package com.example.myapplication.di.modules.datasource

import com.example.myapplication.data.source.UserRemoteSource
import com.example.myapplication.data.source.remote.UserRemoteSourceImpl
import dagger.Module
import dagger.Provides

@Module
class RemoteDataSourceModule {

    @Provides
    fun provideUserRemoteSource(
        remoteSource: UserRemoteSourceImpl
    ): UserRemoteSource {
        return remoteSource
    }

}
