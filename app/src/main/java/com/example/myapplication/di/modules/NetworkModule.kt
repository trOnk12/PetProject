package com.example.myapplication.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Singleton
@Module
class NetworkModule{

    @Singleton
    @Provides
    fun provideRemoteSource(){

    }

    @Singleton
    @Provides
    fun provideLocalSource(){

    }




}
