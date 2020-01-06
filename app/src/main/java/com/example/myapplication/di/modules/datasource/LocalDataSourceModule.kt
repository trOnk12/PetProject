package com.example.myapplication.di.modules.datasource

import android.content.Context
import com.example.myapplication.data.local.sharedpreferences.SharedPreferenceStorage
import com.example.myapplication.data.source.UserLocalSource
import com.example.myapplication.data.source.local.CommentLocalSource
import com.example.myapplication.data.source.local.UserLocalSourceImpl
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
