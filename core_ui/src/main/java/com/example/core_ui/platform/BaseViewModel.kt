package com.example.core_ui.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.exception.Failure
import com.example.core.utills.Event

open class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Event<Failure>> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = Event(failure)
    }

}