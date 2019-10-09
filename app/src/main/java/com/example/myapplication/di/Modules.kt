package com.example.myapplication.di

import com.example.core.network.createNetworkClient
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.network.CommentsAPI
import com.example.myapplication.data.repository.CommentRepositoryImpl
import com.example.myapplication.data.source.remote.CommentRemoteSource
import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.usecase.GetCommentsUseCase
import com.example.myapplication.ui.main.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            networkModule,
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule
        )
    )
}

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }
}

val repositoryModule = module {
    single { CommentRepositoryImpl(remoteSource = get()) as CommentRepository }
}

val dataSourceModule = module {
    single { CommentRemoteSource(commentApi) }
}

val useCaseModule = module {
    factory { GetCommentsUseCase(commentRepository = get()) }
}

val networkModule = module {
    single { commentApi }
}

private val BASE_URL = BuildConfig.BASE_URL
private val retrofit = createNetworkClient(BASE_URL)
private val commentApi = retrofit.create(CommentsAPI::class.java)