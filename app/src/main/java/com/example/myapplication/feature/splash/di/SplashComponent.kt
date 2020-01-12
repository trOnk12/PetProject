package com.example.myapplication.feature.splash.di

import com.example.myapplication.di.components.CoreComponent
import com.example.myapplication.di.scopes.FeatureScope
import com.example.myapplication.feature.splash.SplashActivity
import dagger.Component

@FeatureScope
@Component(
    modules = [SplashModule::class],
    dependencies = [CoreComponent::class]
)
interface SplashComponent {
    fun inject(activity: SplashActivity)
}
