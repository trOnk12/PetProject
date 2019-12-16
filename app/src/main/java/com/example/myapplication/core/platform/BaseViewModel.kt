package com.example.myapplication.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.exception.Failure
import com.example.myapplication.core.Event

open class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Event<Exception>> = MutableLiveData()

    protected fun handleFailure(failure: Exception) {
        this.failure.value = Event(failure)
    }

}