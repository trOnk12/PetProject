package com.example.myapplication.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.domain.model.RegisterData
import com.example.myapplication.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel
@Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel() {

    val registerData: MutableLiveData<RegisterData> = MutableLiveData(RegisterData())

    private val _emailError: MutableLiveData<ValidationError> = MutableLiveData()
    val emailError: LiveData<ValidationError>
        get() = _emailError

    private val _passwordError: MutableLiveData<ValidationError> = MutableLiveData()
    val passwordError: LiveData<ValidationError>
        get() = _passwordError

    fun register() {
        viewModelScope.launch {
            val test = registerUseCase(RegisterData("m.pachulski94@gmail.com", "acostam12"))
        }
    }

}