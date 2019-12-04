package com.example.myapplication

import android.app.Application
import android.content.Context
import com.example.myapplication.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector


class MyApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
            .builder()
            .applicationContext(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

}