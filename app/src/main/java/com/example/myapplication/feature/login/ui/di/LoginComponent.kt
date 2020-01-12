package com.example.myapplication.feature.login.ui.di

import com.example.myapplication.di.components.CoreComponent
import com.example.myapplication.di.scopes.FeatureScope
import com.example.myapplication.feature.login.ui.LoginFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [LoginModule::class],
    dependencies = [CoreComponent::class]
)
interface LoginComponent {
    fun inject(fragment: LoginFragment)
}
