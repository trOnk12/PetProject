package com.example.myapplication.di.components

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.di.modules.*
import com.example.myapplication.di.modules.datasource.LocalDataSourceModule
import com.example.myapplication.di.modules.datasource.RemoteDataSourceModule
import com.example.myapplication.di.modules.network.FireBaseModule
import com.example.myapplication.di.modules.network.NetworkModule
import com.example.myapplication.di.modules.repository.RepositoryModule
import com.example.myapplication.di.modules.viewmodel.ViewModelModule
import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.repository.UserRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        ViewModelModule::class,
        RemoteDataSourceModule::class,
        NetworkModule::class,
        FireBaseModule::class,
        LocalDataSourceModule::class,
        RepositoryModule::class]
)
interface CoreComponent {

    fun context(): Context

    fun viewModelProvider(): ViewModelProvider.Factory

    fun userRepository(): UserRepository

    fun commentRepository(): CommentRepository

}
