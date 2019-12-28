package com.example.myapplication.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.functional.Result
import com.example.myapplication.core.Event
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.data.util.Validator
import com.example.myapplication.domain.model.RegisterData
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel
@Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val validator: Validator
) : BaseViewModel() {

    val registerData: MutableLiveData<RegisterData> = MutableLiveData(RegisterData())

    private val _emailError: MutableLiveData<ValidationError> = MutableLiveData()
    val emailError: LiveData<ValidationError>
        get() = _emailError

    private val _passwordError: MutableLiveData<ValidationError> = MutableLiveData()
    val passwordError: LiveData<ValidationError>
        get() = _passwordError

    private val _repeatPasswordError: MutableLiveData<ValidationError> = MutableLiveData()
    val repeatPasswordError: LiveData<ValidationError>
        get() = _repeatPasswordError

    private val _snackBarMessage: MutableLiveData<Event<String>> = MutableLiveData()
    val snackBarMessage: LiveData<Event<String>>
        get() = _snackBarMessage

    fun register() {
        viewModelScope.launch {
            registerData.value?.let { data ->
                if (validator.validatePasswordWithRepeat(
                        data.password,
                        data.repeatPassword,
                        ::onPasswordError,
                        ::onRepeatPasswordError
                    ) &&
                    validator.validateEmail(data.email, ::onEmailError)
                ) {
                    executeRegistration(data)
                }
            }
        }
    }

    private suspend fun executeRegistration(data: RegisterData) {
        when (val registerResult = registerUseCase(data)) {
            is Result.Success -> handleSuccess(registerResult.data)
            is Result.Error -> handleError(registerResult.exception)
        }
    }

    private fun handleSuccess(data: User) {
        _snackBarMessage.value = Event(data.name + "successfully registered")
    }

    private fun handleError(exception: Exception) {
        exception.message?.let {
            _snackBarMessage.value = Event(it)
        }
    }

    private fun onEmailError(validationError: ValidationError) {
        _emailError.value = validationError
    }

    private fun onPasswordError(validationError: ValidationError) {
        _passwordError.value = validationError
    }

    private fun onRepeatPasswordError(validationError: ValidationError) {
        _repeatPasswordError.value = validationError
    }

}