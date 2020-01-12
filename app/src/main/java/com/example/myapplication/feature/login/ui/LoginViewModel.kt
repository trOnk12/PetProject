package com.example.myapplication.feature.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.functional.Result
import com.example.myapplication.core.commons.livedata.SingleLiveData
import com.example.myapplication.core.commons.base.BaseViewModel
import com.example.myapplication.data.util.InputValidator
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.domain.authentication.AuthenticationSource
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

    private val _event = SingleLiveData<LoginViewEvent>()
    val event: LiveData<LoginViewEvent>
        get() = _event

    fun logIn(source: AuthenticationSource) {
        loginData.value?.let { data ->
            viewModelScope.launch {
                val inputResult = data.copy(source = source)

                if (inputValidator.validatePassword(
                        password = inputResult.password,
                        onError = ::onPasswordError
                    ) &&
                    inputValidator.validateEmail(
                        email = inputResult.email,
                        OnError = ::onEmailError
                    )
                ) {
                    login(inputResult)
                }
            }
        }
    }

    private suspend fun login(data: LoginData) {
        when (val loginResult = logInUseCase(data)) {
            is Result.Success -> {
                _event.value =
                    LoginViewEvent.ShowSnackBarMessage(message = loginResult.data.name + "successfully log in !")
                _event.value = LoginViewEvent.OpenMainActivity
            }
            is Result.Error -> {
                loginResult.exception.message?.let { message ->
                    _event.value = LoginViewEvent.ShowSnackBarMessage(message)
                }
            }
        }
    }

    private fun onEmailError(error: ValidationError) {
        _event.value = LoginViewEvent.ShowEmailError(error)
    }

    private fun onPasswordError(error: ValidationError) {
        _event.value = LoginViewEvent.ShowPasswordError(error)
    }
}
