package com.example.myapplication.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.domain.usecase.SignIn
import kotlinx.coroutines.launch

class LoginViewModel(private val signIn: SignIn) : BaseViewModel() {

    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    fun signInClicked() {
        viewModelScope.launch {
            val email = email.value
            val password = password.value

            if (email != null && password != null) {
                val credentials = Pair(email, password)

                signIn(credentials) {
                    it.either(
                        ::handleFailure,
                        ::handleSignIn
                    )
                }
            }
        }
    }

    private fun handleSignIn(authenticationStatus: AuthenticationStatus) {

    }

}