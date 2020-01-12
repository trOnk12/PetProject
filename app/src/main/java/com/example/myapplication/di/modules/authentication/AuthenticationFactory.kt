package com.example.myapplication.di.modules.authentication

import com.example.myapplication.domain.authentication.Authentication
import com.example.myapplication.domain.authentication.AuthenticationProviderFactory
import com.example.myapplication.domain.authentication.AuthenticationSource
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class AuthenticationFactory
@Inject constructor(
    private val authenticationProviders: MutableMap<AuthenticationSources,
            Provider<Authentication>>
) : AuthenticationProviderFactory {

    override fun create(source: AuthenticationSource): Authentication {
        return authenticationProviders[source]?.get()!!
    }
}

enum class AuthenticationSources : AuthenticationSource {
    FIREBASE
}