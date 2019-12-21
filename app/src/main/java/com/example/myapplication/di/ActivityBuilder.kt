package com.example.myapplication.di

import com.example.myapplication.ui.MainActivity
import com.example.myapplication.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

}