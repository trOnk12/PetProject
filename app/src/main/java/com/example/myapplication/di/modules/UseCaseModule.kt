package com.example.myapplication.di.modules

import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.usecase.GetComments
import dagger.Provides

class UseCaseModule {

    @Provides
    fun provideGetComments(commentRepository: CommentRepository): GetComments {
        return GetComments(commentRepository)
    }

}