package com.example.myapplication.di

import com.example.core.functional.Transformer
import com.example.core.network.createNetworkClient
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.network.CommentService
import com.example.myapplication.data.repository.CommentRepositoryImpl
import com.example.myapplication.data.source.remote.CommentRemoteSource
import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.usecase.GetComments
import com.example.myapplication.presentation.comments.CommentsActivityViewModel
import com.example.myapplication.presentation.route.Navigator
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
            dataSourceModule,
            navigatorModule
        )
    )
}

val viewModelModule = module {
    viewModel { CommentsActivityViewModel(get()) }
}

val repositoryModule = module {
    single { CommentRepositoryImpl(remoteSource = get()) as CommentRepository }
}

val dataSourceModule = module {
    factory { Transformer() }
    single { CommentRemoteSource(get(), get()) }
}

val useCaseModule = module {
    factory { GetComments(commentRepository = get()) }
}

val networkModule = module {
    single { CommentService(retrofit) }
}

val navigatorModule = module {
    single { Navigator() }
}

private const val BASE_URL = BuildConfig.BASE_URL
private val retrofit = createNetworkClient(BASE_URL)
