package com.example.myapplication.feature.register.ui.di

import com.example.myapplication.di.components.CoreComponent
import com.example.myapplication.di.scopes.FeatureScope
import com.example.myapplication.feature.register.ui.RegisterFragment
import dagger.Component


@FeatureScope
@Component(
    modules = [RegisterModule::class],
    dependencies = [CoreComponent::class]
)
interface RegisterComponent {
    fun inject(fragment: RegisterFragment)
}
