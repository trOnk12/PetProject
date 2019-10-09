package com.example.core_ui.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.exception.Failure

open class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

}