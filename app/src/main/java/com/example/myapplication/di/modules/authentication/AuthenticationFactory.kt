package com.example.myapplication.di.modules.authentication

import com.example.myapplication.domain.authentication.AuthenticationProvider
import com.example.myapplication.domain.authentication.AuthenticationProviderFactory
import com.example.myapplication.domain.authentication.AuthenticationSource
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class AuthenticationFactory
@Inject constructor(
    private val authenticationProviders: MutableMap<AuthenticationSource,
            Provider<AuthenticationProvider>>
) : AuthenticationProviderFactory {

    override fun create(source: AuthenticationSource): AuthenticationProvider {
        return authenticationProviders[source]?.get()!!
    }
}
