package com.example.myapplication.di.modules.source

import com.example.myapplication.data.source.IUserSessionLocalSource
import com.example.myapplication.data.source.usersession.local.UserSessionLocalSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideUserSessionLocalSource(
        userSessionLocalSource: UserSessionLocalSource
    ): IUserSessionLocalSource {
        return userSessionLocalSource
    }
}
