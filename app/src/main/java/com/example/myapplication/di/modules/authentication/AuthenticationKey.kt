package com.example.myapplication.di.modules.authentication

import com.example.myapplication.domain.authentication.AuthenticationProvider
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class AuthenticationKey(val value: KClass<out AuthenticationProvider>)
