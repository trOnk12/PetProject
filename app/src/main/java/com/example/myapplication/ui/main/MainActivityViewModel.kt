package com.example.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.network.data.Outcome
import com.example.core.utills.Event
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.usecase.GetCommentsUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel constructor(private val getCommentsUseCase: GetCommentsUseCase) : ViewModel() {

    private val _items = MutableLiveData<List<Comment>>()
    val items: LiveData<List<Comment>>
        get() = _items

    private val _isRefreshing = MutableLiveData<Event<Boolean>>()
    val isRefreshing: LiveData<Event<Boolean>>
        get() = _isRefreshing

    private val _snackBarText = MutableLiveData<Event<String>>()
    val snackBarText: LiveData<Event<String>>
        get() = _snackBarText

    fun fetchComments() {
        viewModelScope.launch {
            getCommentsUseCase.getComment().let { outcome ->
                when (outcome) {
                    is Outcome.Success -> onCommentsLoaded(outcome.value)
                    is Outcome.Failure -> onFailed(outcome.exception, outcome.message)
                }
            }
        }
        _isRefreshing.value = Event(true)
    }

    private fun onCommentsLoaded(comments: List<Comment>) {
        _items.value = comments
    }

    private fun onFailed(exception: Exception, message: String) {
        _snackBarText.value = Event(message)
    }

}