package com.example.myapplication.di.modules.datasource

import com.example.myapplication.data.source.UserLocalSource
import com.example.myapplication.data.source.user.local.UserLocalSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideUserLocalSource(
        userLocalSource: UserLocalSourceImpl
    ): UserLocalSource {
        return userLocalSource
    }

}
