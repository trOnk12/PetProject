package com.example.myapplication.di.modules

import android.content.Context
import com.example.myapplication.data.local.sharedpreferences.SharedPreferenceStorage
import com.example.myapplication.data.source.UserLocalSource
import com.example.myapplication.data.source.local.CommentLocalSource
import com.example.myapplication.data.source.local.UserLocalSourceImpl
import dagger.Module
import dagger.Provides

@Module
class LocalStorageModule {

    @Provides
    fun provideCommentLocalSource(sharedPreferenceStorage: SharedPreferenceStorage): CommentLocalSource {
        return CommentLocalSource(sharedPreferenceStorage)
    }

    @Provides
    fun provideUserLocalSource(sharedPreferenceStorage: SharedPreferenceStorage): UserLocalSource {
        return UserLocalSourceImpl(sharedPreferenceStorage)
    }

    @Provides
    fun providesharedPreferenceStorage(context: Context): SharedPreferenceStorage {
        return SharedPreferenceStorage(context)
    }


}