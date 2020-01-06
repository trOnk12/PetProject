package com.example.myapplication.di.modules.repository

import com.example.myapplication.data.repository.CommentRepositoryImpl
import com.example.myapplication.data.repository.UserRepositoryImpl
import com.example.myapplication.data.source.UserLocalSource
import com.example.myapplication.data.source.UserRemoteSource
import com.example.myapplication.data.source.local.CommentLocalSource
import com.example.myapplication.data.source.remote.CommentRemoteSource
import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCommentRepository(
        repository: CommentRepositoryImpl
    ): CommentRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        repository: UserRepositoryImpl
    ): UserRepository {
        return repository
    }

}
