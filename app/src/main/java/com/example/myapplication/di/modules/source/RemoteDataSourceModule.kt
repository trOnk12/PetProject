package com.example.myapplication.di.modules.source

import com.example.myapplication.data.source.ICommentRemoteSource
import com.example.myapplication.data.source.IUserRemoteSource
import com.example.myapplication.data.source.comment.remote.CommentRemoteSource
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
    ): IUserRemoteSource {
        return userRemoteSource
    }

    @Singleton
    @Provides
    fun provideCommentRemoteSource(
        commentRemoteSource: CommentRemoteSource
    ): ICommentRemoteSource {
        return commentRemoteSource
    }
}
