package com.example.myapplication.data.authentication

import com.example.myapplication.domain.authentication.AuthenticationProvider
import com.example.myapplication.domain.authentication.AuthenticationProviderFactory
import com.example.myapplication.domain.authentication.AuthenticationProviderSource
import javax.inject.Inject
import javax.inject.Provider

class AuthenticationFactory
@Inject constructor(
    private val authenticationProviders: MutableMap<AuthenticationProviderSource,
            Provider<AuthenticationProvider>>
) : AuthenticationProviderFactory() {

    override fun create(source: AuthenticationProviderSource): AuthenticationProvider {
        return authenticationProviders[source]?.get()!!
    }

}