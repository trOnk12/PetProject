package com.example.myapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.domain.model.LoginData
import com.example.myapplication.domain.usecase.LogInUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel
@Inject constructor(
    private val logInUseCase: LogInUseCase
) : BaseViewModel() {

    private val _loginData: MutableLiveData<LoginData> = MutableLiveData()
    val loginData: LiveData<LoginData>
        get() = _loginData

    fun logIn() {
        loginData.value?.let { data ->
            viewModelScope.launch {
                if (data.email.isValid && data.password.isValid) {
                    logInUseCase(data)
                }
            }
        }
    }

}
