package com.example.myapplication.feature.register.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.functional.Result
import com.example.myapplication.core.commons.livedata.SingleLiveData
import com.example.myapplication.core.commons.base.BaseViewModel
import com.example.myapplication.data.util.InputValidator
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.domain.authentication.AuthenticationSource
import com.example.myapplication.domain.usecase.RegisterData
import com.example.myapplication.domain.usecase.RegisterUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

class RegisterViewModel
@Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val inputValidator: InputValidator
) : BaseViewModel() {

    val registerData: MutableLiveData<RegisterData> = MutableLiveData(RegisterData())

    private val _event = SingleLiveData<RegisterViewEvent>()
    val event: LiveData<RegisterViewEvent>
        get() = _event

    fun register(source: AuthenticationSource) {
        viewModelScope.launch {
            registerData.value?.let { data ->
                val inputData = data.copy(source = source)
                if (inputValidator.validatePasswordWithRepeat(
                        password = inputData.password,
                        repeatPassword = inputData.repeatPassword,
                        onError = ::onPasswordError,
                        onRepeatError = ::onRepeatPasswordError
                    ) && inputValidator.validateEmail(
                        email = inputData.email,
                        OnError = ::onEmailError
                    )
                ) {
                    register(inputData)
                }
            }
        }
    }

    private suspend fun register(inputData: RegisterData) {
        when (val result = registerUseCase(inputData)) {
            is Result.Success -> _event.value =
                RegisterViewEvent.ShowSnackBarMessage(message = result.data.name + "successfully registered")
            is Result.Error -> result.exception.message?.let { message ->
                RegisterViewEvent.ShowSnackBarMessage(message)
            }
        }
    }

    private fun onEmailError(error: ValidationError) {
        _event.value =
            RegisterViewEvent.ShowEmailError(error)
    }

    private fun onPasswordError(error: ValidationError) {
        _event.value =
            RegisterViewEvent.ShowPasswordError(error)
    }

    private fun onRepeatPasswordError(error: ValidationError) {
        _event.value =
            RegisterViewEvent.ShowRepeatPasswordError(error)
    }

}
