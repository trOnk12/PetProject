package com.example.myapplication.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.domain.usecase.UserSignIn
import kotlinx.coroutines.launch
import com.example.core.functional.Result
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.usecase.CredentialsParameters


class LoginViewModel(private val userSignIn: UserSignIn) : BaseViewModel() {

    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    fun signInClicked() {
        viewModelScope.launch {
            val email = email.value
            val password = password.value

            if (email != null && password != null) signIn(email,password)
            else displayError()

        }
    }

    private suspend fun signIn(email: String, password: String) {
        val credentials = mapOf(
            CredentialsParameters.EMAIL to email,
            CredentialsParameters.PASSWORD to password
        )

        userSignIn(credentials) {
            when (it) {
                is Result.Success -> handleSignIn(it.data)
                is Result.Error -> handleFailure(it.exception)
            }

        }
    }

    private fun displayError() {

    }

    private fun handleSignIn(data: User) {

    }

}
