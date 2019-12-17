package com.example.myapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.functional.Result
import com.example.myapplication.core.Event
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.data.util.InputValidator
import com.example.myapplication.data.util.ValidateStatus
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.usecase.CredentialsParameters
import com.example.myapplication.domain.usecase.UserSignIn
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userSignIn: UserSignIn,
    private val inputValidator: InputValidator
) : BaseViewModel() {

    val password = MutableLiveData<String>()
    private val _password: String
        get() {
            password.value?.let {
                return it
            }
            return ""
        }

    val email = MutableLiveData<String>()
    private val _email: String
        get() {
            email.value?.let {
                return it
            }
            return ""
        }

    private val snackBarStatus = MutableLiveData<Event<String>>()
    val _snackBarStatus: LiveData<Event<String>>
        get() = snackBarStatus

    fun signInClicked() {
        viewModelScope.launch {
            if (inputValidator.validateEmail(_email, ::invalidEmail) &&
                inputValidator.validatePassword(_password, ::invalidPassword)
            ) {
                signIn(_email, _password)
            }
        }
    }

    private fun invalidPassword(passwordStatus: ValidateStatus.PasswordStatus) {
        when (passwordStatus) {
            is ValidateStatus.PasswordStatus.EmptyPassword -> informUser("Empty password")
        }
    }

    private fun invalidEmail(emailStatus: ValidateStatus.EmailStatus) {
        when (emailStatus) {
            is ValidateStatus.EmailStatus.EmptyEmail -> informUser("Empty e-mail")
        }
    }

    private fun informUser(message: String) {
        snackBarStatus.value = Event(message)
    }


    private suspend fun signIn(email: String, password: String) {
        val credentials = mapOf(
            CredentialsParameters.EMAIL to email,
            CredentialsParameters.PASSWORD to password
        )

        when (val result = userSignIn(credentials)) {
            is Result.Success -> handleSignIn(result.data)
            is Result.Error -> handleFailure(result.exception)
        }
    }

    private fun handleSignIn(user: User) {

    }

}
