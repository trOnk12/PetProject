package com.example.myapplication.di.modules.repository

import com.example.myapplication.data.source.UserLocalSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCommentRepository(
        repository: com.example.myapplication.data.repository.CommentRepository
    ): com.example.myapplication.domain.repository.CommentRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        repository: com.example.myapplication.data.repository.UserRepository
    ): com.example.myapplication.domain.repository.UserRepository {
        return repository
    }

}
