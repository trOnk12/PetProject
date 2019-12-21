package com.example.myapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.data.util.Validator
import com.example.myapplication.domain.model.LoginData
import com.example.myapplication.domain.usecase.LogInUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel
@Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val validator: Validator
) : BaseViewModel() {

    val _loginData: MutableLiveData<LoginData> = MutableLiveData(LoginData())
    val loginData: LiveData<LoginData>
        get() = _loginData

    private val _emailError: MutableLiveData<ValidationError> = MutableLiveData()
    val emailError: LiveData<ValidationError>
        get() = _emailError

    private val _passwordError: MutableLiveData<ValidationError> = MutableLiveData()
    val passwordError: LiveData<ValidationError>
        get() = _passwordError

    fun logIn() {
        _loginData.value?.let { data ->
            viewModelScope.launch {
                if (validator.validatePassword(data.password, ::onPasswordError) &&
                    (validator.validateEmail(data.email, ::onEmailError))
                ) {
                    logInUseCase(data)
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


