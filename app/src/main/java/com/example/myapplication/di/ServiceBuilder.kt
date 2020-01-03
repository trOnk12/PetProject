package com.example.myapplication.di

import com.example.myapplication.data.service.FireStorageService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBuilder {

    @ContributesAndroidInjector
    abstract fun bindFireStorageService(): FireStorageService

}