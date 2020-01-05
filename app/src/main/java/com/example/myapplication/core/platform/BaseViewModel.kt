package com.example.myapplication.core.platform

import androidx.lifecycle.ViewModel
import com.example.myapplication.core.livedata.SingleLiveData

open class BaseViewModel : ViewModel() {

    var failure: SingleLiveData<Exception> = SingleLiveData()

    protected fun handleFailure(failure: Exception) {
        this.failure.value = failure
    }
}
