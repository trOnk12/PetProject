package com.example.myapplication.core.commons.base

import androidx.lifecycle.ViewModel
import com.example.myapplication.core.commons.livedata.SingleLiveData

open class BaseViewModel : ViewModel() {

    var failure: SingleLiveData<Exception> =
        SingleLiveData()

    protected fun handleFailure(failure: Exception) {
        this.failure.value = failure
    }
}
