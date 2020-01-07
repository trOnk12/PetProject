package com.example.myapplication.feature.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.functional.Result
import com.example.myapplication.core.livedata.SingleLiveData
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.data.util.InputValidator
import com.example.myapplication.domain.usecase.LogInUseCase
import com.example.myapplication.domain.usecase.LoginData
import javax.inject.Inject
import kotlinx.coroutines.launch

class LoginViewModel
@Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val inputValidator: InputValidator
) : BaseViewModel() {

    val loginData: MutableLiveData<LoginData> = MutableLiveData(LoginData())

    private val _emailError: MutableLiveData<ValidationError> = MutableLiveData()
    val emailError: LiveData<ValidationError>
        get() = _emailError

    private val _passwordError: MutableLiveData<ValidationError> = MutableLiveData()
    val passwordError: LiveData<ValidationError>
        get() = _passwordError

    private val _snackBarMessage = SingleLiveData<String>()
    val snackBarMessage: LiveData<String>
        get() = _snackBarMessage

    private val _event = SingleLiveData<LoginViewEvent>()
    val event: LiveData<LoginViewEvent>
        get() = _event

    fun logIn() {
        viewModelScope.launch {
            loginData.value?.let { data ->
                if (inputValidator.validatePassword(data.password, ::onPasswordError) &&
                    inputValidator.validateEmail(data.email, ::onEmailError)
                ) {
                    executeLogIn(data)
                }
            }
        }
    }

    private suspend fun executeLogIn(data: LoginData) {
        when (val loginResult = logInUseCase(data)) {
            is Result.Success -> {
                _snackBarMessage.value = loginResult.data.name + "successfully log in !"
                _event.value = LoginViewEvent.OpenMainActivity
            }
            is Result.Error -> {
                loginResult.exception.message?.let {
                    _snackBarMessage.value = it
                }
            }
        }
    }

    private fun onEmailError(validationError: ValidationError) {
        _emailError.value = validationError
    }

    private fun onPasswordError(validationError: ValidationError) {
        _passwordError.value = validationError
    }
}
