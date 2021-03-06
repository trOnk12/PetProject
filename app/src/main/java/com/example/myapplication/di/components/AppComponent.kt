package com.example.myapplication.di.components

import com.example.myapplication.PetProject
import com.example.myapplication.di.modules.*
import com.example.myapplication.di.scopes.AppScope
import dagger.Component

@AppScope
@Component(dependencies = [CoreComponent::class])
interface AppComponent {
    fun inject(application: PetProject)
}
