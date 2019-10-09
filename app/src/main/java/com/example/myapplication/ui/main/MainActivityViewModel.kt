package com.example.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.interactor.UseCase.None
import com.example.core.utills.Event
import com.example.core_ui.platform.BaseViewModel
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.usecase.GetComments
import kotlinx.coroutines.launch

class MainActivityViewModel constructor(private val getComments: GetComments) : BaseViewModel() {

    private val _items = MutableLiveData<List<Comment>>()
    val items: LiveData<List<Comment>>
        get() = _items

    private val _isRefreshing = MutableLiveData<Event<Boolean>>()
    val isRefreshing: LiveData<Event<Boolean>>
        get() = _isRefreshing

    fun fetchComments() {
        viewModelScope.launch {
            getComments(None()) { it.either(::handleFailure, ::handleComments) }
        }
        _isRefreshing.value = Event(true)
    }

    private fun handleComments(comments: List<Comment>) {
        _items.value = comments
    }

}