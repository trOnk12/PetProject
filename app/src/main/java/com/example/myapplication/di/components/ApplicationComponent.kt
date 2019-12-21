package com.example.myapplication.di.components

import android.content.Context
import com.example.myapplication.MyApplication
import com.example.myapplication.di.ActivityBuilder
import com.example.myapplication.di.FragmentBuilder
import com.example.myapplication.di.modules.FireStoreModule
import com.example.myapplication.di.modules.LocalStorageModule
import com.example.myapplication.di.modules.NetworkModule
import com.example.myapplication.di.modules.RepositoryModule
import com.example.myapplication.di.modules.RemoteModule
import com.example.myapplication.di.modules.viewmodel.ViewModelModule
import com.example.myapplication.ui.comment.CommentFragment
import com.example.myapplication.ui.login.LoginFragment
import com.example.myapplication.ui.splash.SplashActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class,
        NetworkModule::class,
        RepositoryModule::class,
        FireStoreModule::class,
        RemoteModule::class,
        LocalStorageModule::class,
        ViewModelModule::class]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MyApplication)
    fun inject(fragment: CommentFragment)
    fun inject(fragment: LoginFragment)
    fun inject(activity: SplashActivity)

}