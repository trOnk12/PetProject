package com.example.myapplication.di.components

import android.content.Context
import com.example.myapplication.PetProject
import com.example.myapplication.di.ActivityBuilder
import com.example.myapplication.di.FragmentBuilder
import com.example.myapplication.di.ServiceBuilder
import com.example.myapplication.di.modules.FireStoreModule
import com.example.myapplication.di.modules.LocalStorageModule
import com.example.myapplication.di.modules.NetworkModule
import com.example.myapplication.di.modules.RemoteModule
import com.example.myapplication.di.modules.RepositoryModule
import com.example.myapplication.di.modules.viewmodel.ViewModelModule
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
        ServiceBuilder::class,
        NetworkModule::class,
        RepositoryModule::class,
        FireStoreModule::class,
        RemoteModule::class,
        LocalStorageModule::class,
        ViewModelModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): AppComponent
    }

    fun inject(application: PetProject)

}
