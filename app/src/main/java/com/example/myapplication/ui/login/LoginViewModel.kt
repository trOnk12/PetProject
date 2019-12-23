package com.example.myapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.functional.Result
import com.example.myapplication.core.Event
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.data.util.Validator
import com.example.myapplication.domain.model.LoginData
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.usecase.LogInUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel
@Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val validator: Validator
) : BaseViewModel() {

    val loginData: MutableLiveData<LoginData> = MutableLiveData(LoginData())

    private val _emailError: MutableLiveData<ValidationError> = MutableLiveData()
    val emailError: LiveData<ValidationError>
        get() = _emailError

    private val _passwordError: MutableLiveData<ValidationError> = MutableLiveData()
    val passwordError: LiveData<ValidationError>
        get() = _passwordError

    private val _navigateToMainActivity: MutableLiveData<Event<User>> = MutableLiveData()
    val navigateToMainActivity: LiveData<Event<User>>
        get() = _navigateToMainActivity

    private val _snackBarMessage: MutableLiveData<Event<String>> = MutableLiveData()
    val snackBarMessage: LiveData<Event<String>>
        get() = _snackBarMessage

    fun logIn() {
        loginData.value?.let { data ->
            viewModelScope.launch {
                if (validator.validatePassword(data.password, ::onPasswordError) &&
                    validator.validateEmail(data.email, ::onEmailError)
                ) {
                    startLogIn(data)
                }
            }
        }
    }

    private suspend fun startLogIn(data: LoginData) {
        when (val loginResult = logInUseCase(data)) {
            is Result.Success -> _navigateToMainActivity.value = Event(loginResult.data)
            is Result.Error -> {
                loginResult.exception.message?.let {
                    _snackBarMessage.value = Event(it)
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


