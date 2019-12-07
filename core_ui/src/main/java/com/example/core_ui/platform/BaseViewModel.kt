package com.example.core_ui.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.exception.Failure
import com.example.myapplication.core.Event

open class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<com.example.myapplication.core.Event<Failure>> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = com.example.myapplication.core.Event(failure)
    }

}