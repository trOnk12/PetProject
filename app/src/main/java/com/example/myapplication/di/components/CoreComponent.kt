package com.example.myapplication.di.components

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.network.service.CommentService
import com.example.myapplication.data.repository.CommentRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.data.source.IUserRemoteSource
import com.example.myapplication.data.source.IUserSessionLocalSource
import com.example.myapplication.di.modules.*
import com.example.myapplication.di.modules.authentication.AuthenticationModule
import com.example.myapplication.di.modules.network.FireBaseModule
import com.example.myapplication.di.modules.network.NetworkModule
import com.example.myapplication.di.modules.repository.RepositoryModule
import com.example.myapplication.di.modules.source.LocalDataSourceModule
import com.example.myapplication.di.modules.source.RemoteDataSourceModule
import com.example.myapplication.di.modules.viewmodel.ViewModelModule
import com.example.myapplication.domain.authentication.AuthenticationProvider
import com.example.myapplication.domain.authentication.AuthenticationProviderFactory
import com.example.myapplication.domain.repository.ICommentRepository
import com.example.myapplication.domain.repository.IUserRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AuthenticationModule::class,
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

    fun userRepository(): IUserRepository

    fun commentRepository(): ICommentRepository

    fun userRemoteSource(): IUserRemoteSource

    fun commentService(): CommentService

    fun userSessionLocalSource(): IUserSessionLocalSource

    fun authenticationProvider(): AuthenticationProviderFactory
}
