package com.example.myapplication.di.modules.repository

import com.example.myapplication.data.repository.CommentRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.domain.repository.ICommentRepository
import com.example.myapplication.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCommentRepository(
        repository: CommentRepository
    ): ICommentRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        repository: UserRepository
    ): IUserRepository {
        return repository
    }
}
