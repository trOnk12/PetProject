package com.example.myapplication.data.authentication

import com.example.myapplication.domain.authentication.AuthenticationProvider
import com.example.myapplication.domain.authentication.AuthenticationProviderFactory
import com.example.myapplication.domain.authentication.AuthenticationSource
import javax.inject.Inject
import javax.inject.Provider

class AuthenticationFactory
@Inject constructor(
    private val authenticationProviders: MutableMap<AuthenticationSource,
            Provider<AuthenticationProvider>>
) : AuthenticationProviderFactory() {

    override fun create(source: AuthenticationSource): AuthenticationProvider {
        return authenticationProviders[source]?.get()!!
    }
}
