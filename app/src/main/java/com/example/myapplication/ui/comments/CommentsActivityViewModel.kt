package com.example.myapplication.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import com.example.core.utills.Event
import com.example.core_ui.platform.BaseViewModel
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.usecase.GetComments
import kotlinx.coroutines.launch

class CommentsActivityViewModel constructor(
    private val getComments: GetComments,
    private val getCommentsDifference: GetCommentsDifference
) : BaseViewModel() {

    private val _items = MutableLiveData<List<Comment>>()
    val items: LiveData<List<Comment>>
        get() = _items

    private val _isRefreshing = MutableLiveData<Event<Boolean>>()
    val isRefreshing: LiveData<Event<Boolean>>
        get() = _isRefreshing

    fun fetchComments() {
        viewModelScope.launch {
            if (items.value == null) getComments(items.value) { it.either(::handleFailure, ::handleComments) }
        }
        _isRefreshing.value = Event(true)
    }

    private fun handleComments(comments: DiffUtil.DiffResult) {
        _items.value = comments
    }

}