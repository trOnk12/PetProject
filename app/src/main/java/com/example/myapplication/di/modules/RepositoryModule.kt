package com.example.myapplication.di.modules

import com.example.myapplication.data.repository.CommentRepositoryImpl
import com.example.myapplication.data.source.remote.CommentRemoteSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCommentRepository(commentRemoteSource: CommentRemoteSource): CommentRepositoryImpl {
        return CommentRepositoryImpl(commentRemoteSource)
    }

}