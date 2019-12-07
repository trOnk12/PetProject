package com.example.myapplication.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.exception.Failure
import com.example.myapplication.core.Event

open class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Event<Failure>> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = Event(failure)
    }

}