package com.example.myapplication.di.modules

import com.example.myapplication.data.repository.CommentRepositoryImpl
import com.example.myapplication.data.source.local.CommentLocalSource
import com.example.myapplication.data.source.remote.CommentRemoteSource
import com.example.myapplication.domain.repository.CommentRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCommentRepository(commentLocalSource: CommentLocalSource,commentRemoteSource: CommentRemoteSource): CommentRepository {
        return CommentRepositoryImpl(commentRemoteSource,commentLocalSource)
    }

}